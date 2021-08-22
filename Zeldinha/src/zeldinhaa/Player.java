package zeldinhaa;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
	
	public int spd = 4;
	public boolean right,up,down,left;
	public boolean movedup;

	public int curAnimation = 0;
	public int frames = 0;
	public int curFrames = 0, targetFrames = 15;
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	public boolean shoot = false;
	public int dir = 1;
	
	public Player(int x,int y) {
		super(x,y,32,32);
	}
	
	public void tick() {
		
		movedup = false;
		
		if (right && World.isFree(x+spd, y)) {
			x+=spd;
			movedup = true;
			dir = 1;
		}else if(left && World.isFree(x-spd, y)) {
			x-=spd;
			movedup = true;
			dir = -1;
		}
		
		if (up && World.isFree(x, y-spd)) {
			y-=spd;
			movedup = true;
		}else if(down && World.isFree(x, y+spd)) {
			y+=spd;
			movedup = true;
		}
		
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
		g.drawImage(Spritesheet.player_front[curAnimation], x,y,32,32, null);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
	}

}
