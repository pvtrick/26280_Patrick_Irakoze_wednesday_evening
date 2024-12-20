package ExceptionHandling;

public class ClassNotFoundException {

	 public static void main(String[] args) {
	        try {
	            // Attempt to load a non-existent class
	            Class.forName("com.example.NonExistentClass");
	        } catch (Exception e) {
	            // Handle the ClassNotFoundException
	            System.out.println("ClassNotFoundException occurred!");
	            System.out.println("Message: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
}
