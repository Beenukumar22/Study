package Interface;

interface call{
	void calling(long num);
}

interface Browse{
	void browsing(String url);
}

class mobile implements call, Browse{
	@Override
	public void calling(long num) {
		System.out.println("Calling " + num);
	}
	
	@Override
	public void browsing(String url) {
		System.out.println("Browsing to " + url);
	}
}

public class Phone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mobile mo = new mobile();
		mo.calling(9600778);
		mo.browsing("www.google.com");

	}

}
