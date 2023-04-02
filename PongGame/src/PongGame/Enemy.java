package PongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Enemy {

	private int width = 45, height = 5;
	public double x, y, speed = 3;
	
	public boolean right, left;
	
	public Enemy() {
		x = Game.WIDTH/2-width/2;
		y = 0;
	}
	
	public void tick() {		
//		right = Game.keys[KeyEvent.VK_RIGHT];
//		left = Game.keys[KeyEvent.VK_LEFT];
		
		x = Game.keys[KeyEvent.VK_D] ? x+speed : Game.keys[KeyEvent.VK_A] ? x-speed: x;
		x = x + width > Game.WIDTH ? Game.WIDTH-width : x < 0 ? 0 : x;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, width, height);
	}
}
