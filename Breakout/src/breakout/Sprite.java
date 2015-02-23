package breakout;

import java.awt.Graphics2D;

public abstract class Sprite {
	double x;
	double y;
	double vx;
	double vy;
	boolean isExist;
	
	public Sprite(double x, double y){
		this.x = x;
		this.y = y;
		isExist = true;
	}
	
	public abstract void checkOver();
	
	public abstract void update();
	
	public abstract void draw(Graphics2D g);
	
//	public abstract boolean collision(Ball b);
	
	public boolean isExist(){
		return isExist;
	}
}
