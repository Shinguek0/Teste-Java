package zeldinhaa;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle {
	
	public int spd = 3;
	public int right = 1,up = 0,down = 0,left = 0;
	public boolean movedup;

	public int curAnimation = 0;
	public int frames = 0;
	public int curFrames = 0, targetFrames = 15;
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	public boolean shoot = false;
	public int dir = 1;
	
	public Enemy(int x,int y) {
		super(x,y,32,32);
	}
	
	public void perseguirPlayer() {
		Player p = game.player;
		if(x < p.x && World.isFree(x + spd, y)) {
			if(new Random().nextInt(100) < 50) {
				x+=spd;
			}
		}else if(x > p.x && World.isFree(x - spd, y)) {
			if(new Random().nextInt(100) < 50) {
				x-=spd;
			}
		}
		
		if(y < p.y && World.isFree(x, y + spd)) {
			if(new Random().nextInt(100) < 50) {
				y+=spd;
			}
		}else if(y > p.y && World.isFree(x , y-spd)) {
			if(new Random().nextInt(100) < 50) {
				y-=spd;
			}
			}
		
		}
		
	
	public void tick() {
		
		movedup = false;
		perseguirPlayer();

		if(movedup) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == 2) {
					curAnimation = 0;
				}
				
			}
			
		
		}
		
		if(shoot) {
			frames++;
			if(frames == 5) {
				frames = 0;
				shoot = false;
				bullets.add(new Bullet(x,y,dir));
			}
		}
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.enemy, x, y, 32, 32, null);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
	}

}

