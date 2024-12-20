package ExceptionHandling;

public class NullPointerException {

	public static void main(String[] args) {
        try {
            // Simulate a null reference
            String text = null;

            // Attempt to access a method on the null reference
            int length = text.length(); // This line will throw NullPointerException
            System.out.println("Length of text: " + length);
        } catch (Exception e) {
            // Handle the NullPointerException
            System.out.println("NullPointerException occurred!");
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
