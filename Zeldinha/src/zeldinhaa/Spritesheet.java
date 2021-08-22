package zeldinhaa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	public static BufferedImage spritesheet;
	public static BufferedImage[] player_front;
	public static BufferedImage spike;
	public static BufferedImage enemy;
	
	
	public Spritesheet() {
		
		
		try {
			spritesheet = ImageIO.read(getClass().getResource("/aula05-spritesheet.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		player_front = new BufferedImage[4];
		player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
		player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);
		player_front[2] = Spritesheet.getSprite(34, 11, 16, 16);
		player_front[3] = Spritesheet.getSprite(50, 11, 16, 16);
		spike = Spritesheet.getSprite(191, 185, 16, 16);
		enemy = Spritesheet.getSprite(125, 258, 16, 16);
		
		
		
	}
	
	public static BufferedImage getSprite(int x,int y,int width,int height) {
		return spritesheet.getSubimage(x, y, width, height);
		
	}
	
	
	
	
}
