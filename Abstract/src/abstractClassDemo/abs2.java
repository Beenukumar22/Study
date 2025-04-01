package abstractClassDemo;

abstract class bankAccount{
	private String name;
	private double balance;
		
	public bankAccount(String name, double balance) {
		super();
		this.name = name;
		this.balance = balance;
	}
	
	abstract void intrest();
	
	public void showbalance() {
		System.out.println("Account Holder: " + name);
        System.out.println("Balance: $" + balance);
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
		
}

class sacc extends bankAccount{
	double intrestrate;
	
	
	public sacc(String name, double balance, double intestrate) {
		super(name, balance);
		this.intrestrate = intestrate;
	}

	@Override 
	public void intrest(){
		double in = intrestrate * getBalance() / 100;
		setBalance(in + getBalance() );
		System.out.println("Intrest you got " + in);
		
	}
	
}

public class abs2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sacc ba = new sacc("beenu" , 1000 , 10);
		ba.showbalance();
		ba.intrest();
		ba.showbalance();
		
	}

}
