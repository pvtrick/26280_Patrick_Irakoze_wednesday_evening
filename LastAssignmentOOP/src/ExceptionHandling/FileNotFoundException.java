package ExceptionHandling;
import java.io.*;

public class FileNotFoundException {
	

	public class FileNotFoundExceptionExample {
	    public static void main(String[] args) {
	        try {
	            // Trying to open a file that does not exist
	            FileReader file = new FileReader("missingfile.txt");
	        } catch (Exception e) {
	            // Handling the FileNotFoundException
	            System.out.println("FileNotFoundException handled: " + e.getMessage());
	        }
	    }
	}

	
}
