package ExceptionHandling;

    import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class SQLExceptionExample {
	    public static void main(String[] args) {
	        // JDBC URL for a non-existent database
	        String url = "jdbc:mysql://localhost:3306/nonexistent_db";
	        String username = "root";
	        String password = "";

	        Connection connection = null;

	        try {
	            // Attempt to establish a connection
	            connection = DriverManager.getConnection(url, username, password);
	            System.out.println("Connection successful!");

	            // Create a Statement object
	            Statement statement = connection.createStatement();

	            // Attempt to execute invalid SQL
	            String invalidSQL = "SELECT * FRM some_table"; // 'FROM' is misspelled
	            statement.executeQuery(invalidSQL);
	        } catch (SQLException e) {
	            // Handle the SQLException
	            System.out.println("SQLException occurred!");
	            System.out.println("Error Code: " + e.getErrorCode());
	            System.out.println("SQL State: " + e.getSQLState());
	            System.out.println("Message: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            // Clean up resources
	            try {
	                if (connection != null) {
	                    connection.close();
	                    System.out.println("Connection closed.");
	                }
	            } catch (SQLException e) {
	                System.out.println("Failed to close the connection.");
	                e.printStackTrace();
	            }
	        }
	    }
	}


