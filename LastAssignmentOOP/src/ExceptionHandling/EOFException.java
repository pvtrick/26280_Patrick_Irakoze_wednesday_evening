package ExceptionHandling;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class EOFException {

	    public static void main(String[] args) throws IOException {
	        try (DataInputStream dis = new DataInputStream(new FileInputStream("example.txt"))) {
	            // Trying to read beyond the end of a file
	            while (true) {
	                dis.readByte();
	            }
	        } catch (Exception e) {
	            // Handling the EOFException
	            System.out.println("EOFException handled: End of file reached.");
	        }
	    }
	}


