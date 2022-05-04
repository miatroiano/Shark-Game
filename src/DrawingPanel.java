import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Year;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingPanel extends JPanel {

	private Timer timer;
	private boolean isGameOver = false;
	private ArrayList<DrawingPanelListener> listeners = new ArrayList<DrawingPanelListener>();
	private Sprite shark;
	private Sprite pika;
	private boolean wPressed;
	private boolean sPressed;
	int randnum = 0;
	int speed = 1;
	
	
	public DrawingPanel() {
		
		Random rand = new Random(); 
		shark = new Sprite("./images/shark.png"); 
		shark.setLocation(-185, 100);
		pika = new Sprite("./images/pikachu.png");
		pika.setLocation(950, 200);
		this.setBackground(Color.blue);
		
		
			randnum = rand.nextInt(10 , 500);
        
		
		timer = new Timer(1, new ActionListener() {
			int y = 100;
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				shark.setLocation(shark.getX() + speed,y);
	        	
	        	if(DrawingPanel.this.getWidth() <= shark.getX()) {
	        		y = y + randnum;
	        		
	        		shark.setLocation(-185, randnum = rand.nextInt(10,500));
	        		
	        		repaint();
	        		y = randnum;
	        		speed ++;
	        	}
	        	
	        	if(DrawingPanel.this.getHeight() - 33 <= pika.getY()) {
	        		pika.setLocation(950, 682);
	        	}
	        	if( pika.getY() <= 0) {
	        		pika.setLocation(950, 5);
	        	}
	        	
				if (wPressed) {
					pika.setLocation(
					pika.getX() ,
					pika.getY() - 5);
					
					}
				else if (sPressed) {
					pika.setLocation(
					pika.getX() ,
					pika.getY() + 5);
					
					}
					repaint();
					
				
					
				if(pika.intersects(shark)) {
					isGameOver = true;
				}
				
				if (isGameOver) {
					gameOver();
				}
				
				
				repaint();
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					wPressed = true;
					}
				else if (e.getKeyCode() == KeyEvent.VK_S) {
					sPressed = true;
					}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					wPressed = false;
					}
					else if (e.getKeyCode() == KeyEvent.VK_S) {
					sPressed = false;
					}

			}
		});
		
		this.setFocusable(true);
		this.setDoubleBuffered(true);
	}
	
	public void addListener(DrawingPanelListener listener) {
		listeners.add(listener);
	}
	
	public void startGame() {
		isGameOver = false;
		timer.start();
	}
	
	public void togglePause() {
		if (timer.isRunning()) {
			timer.stop();
		}
		else {
			timer.start();
		}
	}
	
	public void gameOver() {
		isGameOver = true;
		timer.stop();
		for (DrawingPanelListener listener : listeners) {
			listener.onGameOver();
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		Graphics2D brush = (Graphics2D)g;
		shark.paint(brush);
		pika.paint(brush);
		if (isGameOver) {
			brush.setColor(Color.red);
			brush.setFont(new Font("Times New Roman", Font.PLAIN, 72));
			brush.drawString("GAME OVER", 300, 300);
		}
	}
}
