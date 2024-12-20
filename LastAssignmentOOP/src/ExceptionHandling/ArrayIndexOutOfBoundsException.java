package ExceptionHandling;

public class ArrayIndexOutOfBoundsException {

	 public static void main(String[] args) {
	        try {
	            // Create an array with 3 elements
	            int[] numbers = {1, 2, 3};

	            // Attempt to access an invalid index
	            int invalidIndexValue = numbers[5]; // Index 5 is out of bounds
	            System.out.println("Value at index 5: " + invalidIndexValue);
	        } catch (Exception e) {
	            // Handle the ArrayIndexOutOfBoundsException
	            System.out.println("ArrayIndexOutOfBoundsException occurred!");
	            System.out.println("Message: " + e.getMessage());
	            e.printStackTrace();
	        }
	        
	 }
}
