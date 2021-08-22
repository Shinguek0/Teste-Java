package com.Shin.main;

//CURENTLY WORKING ON
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	private boolean isRunning;
	private Thread thread;
	public static JFrame frame;
	private final int WIDTH = 160, HEIGHT = 120, SCALE = 4;
	private ArrayList<Entities> ent = new ArrayList<>();
	
	private BufferedImage image;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		ent.add(new Entities());
	}
	
	public void initFrame() {
		frame = new JFrame("'-'");
		frame.add(this);
		frame.setResizable(true);  // passar para false
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	public void tick() {
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color( 100,200,200));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//Screen Square
		
		// girar//////
		g.setColor(Color.black);
		g.fillRect(47, 9, 16/SCALE , 16/SCALE);
		g.setFont(new Font("Arial",Font.BOLD,9));
		g.setColor(Color.red);
		g.drawString("Objetivo: ", 3, 14);
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0,0,WIDTH*SCALE,HEIGHT*SCALE, null);
		bs.show();
		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
				
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("fps " + frames);
				frames = 0;
				timer += 1000;
			}			
		}
		stop();
	}
}
