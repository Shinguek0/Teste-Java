package zeldinhaa;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

	public int vert = 7*32;
	public static List<Blocks> blocks = new ArrayList<Blocks>();
	
	public World() {
		
		for(int xx = 1;xx < 19;xx++) {
				blocks.add(new Blocks(xx*32,vert));
				if (xx == 7) {
					vert-=4*32;
				}
			}
	
		for(int xx = 0;xx < 12;xx++) {
			blocks.add(new Blocks(xx*32,0));
			}
		
		for(int xx = 0;xx < 20;xx++) {
			blocks.add(new Blocks(xx*32,480));
			}
		
		for(int xx = 0;xx < 15;xx++) {
			blocks.add(new Blocks(0,xx*32));
			}
		
		for(int xx = 0;xx < 15;xx++) {
			blocks.add(new Blocks(640-32,xx*32));
			}
	}
	
	public static boolean isFree(int x, int y) {
		for(int i = 0;i < blocks.size();i++) {
			Blocks blocoAtual = blocks.get(i);
			if(blocoAtual.intersects(new Rectangle(x,y,32,32))) {
				return false;
			}
		}
		return true;
		
	}
	
	public void render(Graphics g) {
		for(int i = 0;i < blocks.size();i++) {
			blocks.get(i).render(g);
		}
		
	}

}
