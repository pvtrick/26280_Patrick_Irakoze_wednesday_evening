package ExceptionHandling;

public class ArithmeticException {

	 public static void main(String[] args) {
	        try {
	            // Simulate division by zero
	            int numerator = 10;
	            int denominator = 0;
	            int result = numerator / denominator; // This line will throw ArithmeticException
	            System.out.println("Result: " + result);
	        } catch (Exception e) {
	            // Handle the ArithmeticException
	            System.out.println("ArithmeticException occurred!");
	            System.out.println("Message: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
}
