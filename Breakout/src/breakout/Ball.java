package breakout;

import java.awt.Graphics2D;

public class Ball extends Sprite{
	int r;
	int R;
	final static double VMAX = 5;
	final static double VMAX2 = 25;
	final static double XMAX = 4;
	double vx;
	double vy;
	
	public Ball(double x, double y) {
		super(x, y);
		this.r = 10;
		this.R = r*2;
		vx = 2;
		vy = Math.sqrt(VMAX2-vx*vx);
	}
	
	@Override
	public void update() {
//		double oldx = x;
//		double oldy = y;
		x += vx;
		y += vy;
		checkOver();
	}
	
	public void changeV(boolean isx){
		if(isx) vx = -vx;
		else vy = -vy;
	}
	
	@Override
	public void checkOver() {
		if(x < r){
			x = r;
			vx = -vx;
		}
		else if(x > MainFrame.WIDTH-r){
			x = MainFrame.WIDTH-r;
			vx = -vx;
		}
		else if(y < r){
			y = r;
			vy = -vy;
		}
		else if(y > MainFrame.HEIGHT-r){
			y = MainFrame.HEIGHT-r;
			vy = -vy;
			delete();
		}
	}
	
	public void delete(){
		isExist = false;
	}
	
//	private boolean checkWall(){
//		return x < r || x > MainFrame.WIDTH-r || y < r || y > MainFrame.HEIGHT-r;
//	}
	
	@Override
	public void draw(Graphics2D g) {
		g.fillOval((int)(x-r), (int)(y-r), R, R);
	}
	
//	@Override
//	public boolean collision(Ball b) {
//		
//		return false;
//	}
}
