package Exercises;
import java.util.*;

interface Discount  {
	
 public void applyDiscount(double price);
	 
}

public  abstract  class TicketBooking  implements Discount{
	 String CustomerName;
	  String Location;
	  String Destination;
	  String TicketType; 
	  
	 
 public void bookTicket() {
	 
}
 public String  getDetails(String Name,String Location,String Destination,String  gender ,int age) {
	  
      Scanner sc = new Scanner(System.in);
	  System.out.println("Enter your name : ");
	  CustomerName=sc.next();
	  System.out.println("Enter your location : ");
	  Location =sc.next();
	  System.out.println("Enter your Destion : ");
	  Destination = sc.next();  
	  System.out.println("Enter the your age ");
	  age = sc.nextInt();
	  return Name;
	  
}

}

class BusinessBooking extends TicketBooking{
	public void bookTicket() {
		
		System.out.println("names : "+CustomerName);
		System.out.println("location : "+Location);
		System.out.println("Destination : "+Destination);
		
		System.out.println("ticket booked ");
	}

	@Override
	public void applyDiscount(double price) {
		// TODO Auto-generated method stub
		
	}
	
}
 class EconomicClassBooking extends TicketBooking{

	@Override
	public void applyDiscount(double price) {
		// TODO Auto-generated method stub
		
	}
	 
	 
 }
class main {
	public static void main(String[] args) {
		BusinessBooking bb =new BusinessBooking();
		bb.bookTicket();
	}
}

