package PongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	private int width = 10, height = 10;
	public double x, y, dx, dy, speed = 1.8;
	
	public int angle;
	
	public Ball() {
		x = Game.WIDTH/2-width/2;
		y = Game.HEIGHT/2-height/2;
		
		angle = new Random().nextInt(75) + 46;
		
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
		
		dy *= (new Random().nextGaussian()) > 0 ? 1: -1;
		dx *= (new Random().nextGaussian()) > 0 ? 1: -1;
	}
	
	public void tick() {	
		if(x >= Game.player.x && x <= Game.player.x+60 && y >= Game.player.y-10) {
			angle = new Random().nextInt(75) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			speed = new Random().nextDouble(3.6)+1.4;
			dy*= dy > 0 ? -1 : 1;
		}
		else if(x >= Game.enemy.x && x <= Game.enemy.x+60 && y <= Game.enemy.y+5) {			
			angle = new Random().nextInt(75) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			speed = new Random().nextDouble(3.2)+1.4;
			dy*= dy < 0 ? -1 : 1;
		}
		else if(y > Game.player.y-5 || y < Game.enemy.y) {
			new Game();
			return;
		}
		
		if(x > Game.WIDTH-width || x < 0)
			dx = -dx;

		x += dx*speed;
		y += dy*speed;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillOval((int)x, (int)y, width, height);
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
}
