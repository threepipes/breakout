package breakout;

public class Block extends Rect{
	public Block(double x, double y) {
		super(x, y);
		this.height = 18;
		this.width = 48;
	}
	
	public void delete(){
		isExist = false;
	}
}
