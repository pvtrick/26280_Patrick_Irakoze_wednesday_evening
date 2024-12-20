package ExceptionHandling;

public class IllegalArgumentException {

	// Method to calculate the square root of a number
    public static double calculateSquareRoot(double number) throws Exception {
        if (number < 0) {
            throw new Exception("Number must be non-negative.");
        }
        return Math.sqrt(number);
    }

    public static void main(String[] args) {
        try {
            // Valid argument
            double validResult = calculateSquareRoot(16);
            System.out.println("Square root of 16 is: " + validResult);

            // Invalid argument: Passing a negative number
            double invalidResult = calculateSquareRoot(-9); // This will throw IllegalArgumentException
            System.out.println("Square root of -9 is: " + invalidResult);

        } catch (Exception e) {
            System.err.println("IllegalArgumentException occurred: " + e.getMessage());
        }
    }
}

