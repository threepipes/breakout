package breakout;

import java.awt.Graphics2D;

public class Rect extends Sprite{
	int width;
	int height;
	
	public Rect(double x, double y) {
		super(x, y);
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void checkOver() {
		if(x < 0) x = 0;
		else if(x >= MainFrame.WIDTH-width) x = MainFrame.WIDTH-width;
//		else if(y < height) y = height;
//		else if(y > MainFrame.HEIGHT-height) y = MainFrame.HEIGHT-height;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public int collision(Ball b) {
		if(Math.abs(b.x - x)+Math.abs(b.y - y) > 100) return -1;
		double[] px = new double[4];
		double[] py = new double[4];
		px[0] = x;
		py[0] = y;
		px[1] = x + width;
		py[1] = y;
		px[2] = x + width;
		py[2] = y + height;
		px[3] = x;
		py[3] = y + height;
		for(int i=0; i<4; i++){
			double dist = (b.x-px[i])*(b.x-px[i]) + (b.y-py[i])*(b.y-py[i]);
//			if(dist < b.r*b.r) return i;
			double pqx = px[(i+1)%4] - px[i];
			double pqy = py[(i+1)%4] - py[i];
			double pmx = b.x - px[i];
			double pmy = b.y - py[i];
			double k = ((pqx*pmx) + (pqy*pmy))/((pqx*pqx) + (pqy*pqy));
			if(0 < k && k < 1){
				double mh = dist - k*((pqx*pmx) + (pqy*pmy));
				if(mh < b.r*b.r) return i;
			}
		}
		return -1;
	}
}
