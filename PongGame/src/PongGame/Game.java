package PongGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 200, HEIGHT = 170, SCALE = 3;

	private BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
	
	public static boolean[] keys = new boolean[256];
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		
		player = new Player();
		enemy = new Enemy();
		ball = new Ball();
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		JFrame frame = new JFrame("Pong Game");
		
		frame.setResizable(false);
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}

	public void tick() {
		
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) layer.getGraphics();
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(100, 100, 100));

		for(int i = 0; i <= WIDTH; i+=WIDTH/10) 
			g.drawLine(i+7, HEIGHT/2, i-7, HEIGHT/2);
		
		ball.render(g);
		player.render(g);
		enemy.render(g);
		
		Graphics g2 = bs.getDrawGraphics();
		g2.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		bs.show();
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
