package breakout;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MainFrame extends JPanel
	implements Runnable, KeyListener{
	public static final int WIDTH = 300;
	public static final int HEIGHT = 500;
	
	private Ball ball;
	private Block[] block;
	private Bar bar;
	private boolean anime;
	private int key;
	public MainFrame(){
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setSize(WIDTH, HEIGHT);
		init();
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
		anime = true;
	}
	
	public void init(){
		ball = new Ball(50, 200);
		block = new Block[12];
		for(int i=0; i<3; i++){
			for(int j=0; j<4; j++){
				block[i*4+j] = new Block(51+j*50, 52+i*20);
			}
		}
		bar = new Bar(WIDTH/2-Bar.WIDTH/2, 400, ball);
	}
	
	public void update(){
		keyCheck();
		ball.update();
		for(int i=0; i<12; i++){
//			block[i].update();
			if(!block[i].isExist()) continue;
			int check = block[i].collision(ball);
			if(check == -1) continue;
			if(check == 0 || check == 2) ball.changeV(false);
			else ball.changeV(true);
			block[i].delete();
		}
		bar.update();
		int check = bar.collision(ball);
		if(check != -1){
			ball.changeV(false);
			ball.setYon(400);
		}
//		else if(check != -1) ball.changeV(true);
		
		if(!ball.isExist()) anime = false;
	}
	
	public void keyCheck(){
		switch(key){
		case KeyEvent.VK_LEFT:
			bar.move(-5);
			break;
		case KeyEvent.VK_RIGHT:
			bar.move(5);
			break;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		key = e.getKeyCode();
//		System.out.println(key);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		key = 0;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		if(ball.isExist()) ball.draw(g);
		for(int i=0; i<12; i++){
			if(!block[i].isExist()) continue;
			block[i].draw(g);
		}
		bar.draw(g);
		
		
		if(!anime) g.drawString("GAME OVER", 110, 200);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		draw((Graphics2D)g);
	}
	
	@Override
	public void run() {
		while(true){
			if(anime){
				update();
			}
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
