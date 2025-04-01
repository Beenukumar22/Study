package Interface;

interface draw{
	public void draww();
}
interface moveTo{
	public void move(int x,int y);
}
interface area{
	public int area();
}

class circle implements draw , moveTo, area{
	int len,wid;

	public circle(int len, int wid) {
		super();
		this.len = len;
		this.wid = wid;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("the circle is moved from " + x + " to " + y);
		
	}

	@Override
	public void draww() {
		// TODO Auto-generated method stub
		System.out.println("The circle is drawn");
		
	}
	
	@Override
	public int area() {
		return (len * wid);
	}
	
	
}

public class Inter {

	public static void main(String[] args) {
		circle c = new circle(5, 7);
        c.draww();
        c.move(3, 5);
        System.out.println("Area: " + c.area());
		
	}

}
