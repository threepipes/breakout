package breakout;

import java.awt.Graphics2D;

public class Bar extends Rect{
	public static final int WIDTH = 60;
	public static final int HEIGHT = 15;
	private Ball ball;
	public Bar(double x, double y, Ball b) {
		super(x, y);
		this.width = WIDTH;
		this.height = HEIGHT;
		this.ball = b;
	}
	
	@Override
	public void update() {
		checkOver();
	}
	
	public void move(int dx){
		this.x += dx;
	}
	

}
