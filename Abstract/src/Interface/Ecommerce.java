package Interface;
import java.util.Scanner;

interface payments{
	boolean verifyDetails(String Details) ;
	public void processPayment(double amount);
}
//card
class creditCard implements payments{
	private String CardNumber;
	private String cvv;
	private String ExpireDate;
	
	public creditCard(String cardNumber, String cvv, String expireDate) {
		super();
		this.CardNumber = cardNumber;
		this.cvv = cvv;
		this.ExpireDate = expireDate;
	}
	
	
	@Override
	public boolean verifyDetails(String Details) {
		String[] AllDetails = Details.split(",");
		if (AllDetails.length != 3){
			return false;
		}
		
		 return AllDetails[0].equals(CardNumber) &&
	            AllDetails[1].equals(cvv) &&
	            AllDetails[2].equals(ExpireDate);
	}
	
	@Override
	public void processPayment(double amount) {
		System.out.println("Successfully paid " + amount);
	}
}

//UPI 
class UPI implements payments {
 private String upiId;


 public UPI(String upiId) {
     this.upiId = upiId;
 }

 @Override
 public boolean verifyDetails(String details) {
     return details.equals(upiId);
 }

 @Override
 public void processPayment(double amount) {
     System.out.println("Processing UPI payment of $" + amount);
   }
 
}

//paypal
class PayPal implements payments {
 private String email;

 public PayPal(String email) {
     this.email = email;
 }

 @Override
 public boolean verifyDetails(String details) {
     return details.equals(email);
 }

 @Override
 public void processPayment(double amount) {
     System.out.println("Processing PayPal payment of $" + amount);
 }
}



class PaymentProcessor {
 private payments Pay;

 public PaymentProcessor(payments payment) {
     this.Pay = payment;
 }

 public void makePayment(String details, double amount) {
     if (Pay.verifyDetails(details)) {
         Pay.processPayment(amount);
         System.out.println("Payment successful!");
     } else {
         System.out.println("Payment verification failed. Please check your details.");
     }
 }
}


public class Ecommerce {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     System.out.println("Select Payment Method:");
     System.out.println("1. Credit Card");
     System.out.println("2. UPI");
     System.out.println("3. PayPal");
     System.out.print("Enter your choice: ");
     int choice = scanner.nextInt();
     scanner.nextLine(); // Consume newline

     payments payment = null;
     String cardDetails = "";

     switch (choice) {
         case 1:
             System.out.print("Enter Card Number: ");
             String cardNumber = scanner.nextLine();
             
             System.out.print("Enter CVV: ");
             String cvv = scanner.nextLine();

             System.out.print("Enter Expiry Date (MM/YY): ");
             String expiryDate = scanner.nextLine();

            

             payment = new creditCard(cardNumber,cvv, expiryDate);

             System.out.print("Enter card details to verify (format: cardNumber,cvv,expiryDate): ");
             cardDetails = scanner.nextLine();

             break;

         case 2:
             System.out.print("Enter UPI ID: ");
             String upiId = scanner.nextLine();

             payment = new UPI(upiId);

             System.out.print("Enter UPI ID to verify: ");
             String upiDetails = scanner.nextLine();
             cardDetails = upiDetails;
             break;

         case 3:
             System.out.print("Enter PayPal Email: ");
             String email = scanner.nextLine();

             payment = new PayPal(email);

             System.out.print("Enter PayPal email to verify: ");
             String paypalDetails = scanner.nextLine();
             cardDetails = paypalDetails;
             break;

         default:
             System.out.println("Invalid choice! Please select a valid option.");
             System.exit(0);
     }

     // Process Payment
     if (payment != null) {
         System.out.print("Enter Amount: ");
         double amount = scanner.nextDouble();

         PaymentProcessor paymentProcessor = new PaymentProcessor(payment);
         
         // Verify and process payment
         if (payment.verifyDetails(cardDetails)) {
             paymentProcessor.makePayment(cardDetails, amount);
         } else {
             System.out.println("Payment verification failed. Please check your details.");
         }
     }

     scanner.close();
 }
}

