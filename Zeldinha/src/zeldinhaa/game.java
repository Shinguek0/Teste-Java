package zeldinhaa;

/**importaçoes**/
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class game extends Canvas implements Runnable, KeyListener{
	
	public static int WIDTH = 640, HEIGHT = 480;
	public static int SCALE = 3;
	public static Player player;
	public World world;
	
	public List<Enemy> enemy = new ArrayList<Enemy>();
	
	/** dimensao da tela **/
	public game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		player = new Player(240,448);
		world = new World();
		enemy.add(new Enemy(50,50));
		enemy.add(new Enemy(50,50));
	}
	
	public void tick() {
		player.tick();
		
		for(int i = 0; i < enemy.size(); i++) {
			enemy.get(i).tick();
		}
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);
		new Spritesheet();
		player.render(g);
		for(int i = 0; i < enemy.size(); i++) {
			enemy.get(i).render(g);
		}
		world.render(g);
		
		bs.show();	
	}

	public static void main(String[] args) {
		/** Template Basico **/
		game game = new game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Zeldinha");
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		/** Thread para startar o void run **/
		new Thread(game).start();

	}

	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			player.shoot = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.spd = 6;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.spd = 4;
		}
		
	}
}
