package abstractClassDemo;

abstract class Shape{
	String name = " ";

	public Shape(String name) {
		super();
		this.name = name;
	}
	
	 public void moveTo(int x, int y) {
	        System.out.println(this.name + " has been moved to x = " + x + " and y = " + y);
	 }
	 
	 abstract public void draw ();
	 abstract public double area();
		
}

class Rectangle extends Shape {
	int length , width ;

	public Rectangle(String name, int length, int width) {
		super(name);
		this.length = length;
		this.width = width;
	}
	
	@Override
	 public void draw() {
        System.out.println("Rectangle has been drawn ");
    }

    @Override 
    public double area() {
        return (length * width);
    }
	
}

class Circle extends Shape{
	double pi = 3.14;
	int radius ;
	public Circle(String name,  int radius) {
		super(name);
		this.radius = radius;
	}
	
	@Override 
	public void draw() {
		System.out.println("Circle has beeen drawn");
	}
	
	@Override
	public double area() {
		return (double)(pi * radius * radius);
	}
}

public class Abs{
	public static void main(String[] args) {
		Shape rect =new  Rectangle("Rectangle" , 5 , 7);
		System.out.println("Area of rectangle: " + rect.area());
	    rect.moveTo(1, 2);
        System.out.println();

	    Shape circle = new Circle("Circle", 2);
	    System.out.println("Area of circle: " + circle.area());
	    circle.moveTo(2, 4);
	    }
		
	}
