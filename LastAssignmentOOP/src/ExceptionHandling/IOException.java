package ExceptionHandling;
import java.io.*;

public class IOException {

	    public static void main(String[] args) {
	        try {
	            // Trying to read from a non-existent file
	            BufferedReader reader = new BufferedReader(new FileReader("nonexistent.txt"));
	            reader.readLine();
	        } catch (Exception e) {
	            // Handling the IOException
	            System.out.println("IOException handled: " + e.getMessage());
	        }
	    }
	}


