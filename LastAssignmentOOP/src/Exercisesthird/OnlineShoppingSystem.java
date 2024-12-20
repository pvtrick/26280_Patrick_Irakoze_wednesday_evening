package Exercisesthird;
import java.util.*;

// Custom Exception for Registration Errors
class RegistrationException extends Exception {
    public RegistrationException(String message) {
        super(message);
    }
}

// Custom Exception for Login Errors
class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }
}

// Custom Exception for Cart Operations
class CartException extends Exception {
    public CartException(String message) {
        super(message);
    }
}

// Custom Exception for Payment Errors
class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}

// User Class
class User {
    private String email;
    private String password;

    public User(String email, String password) throws RegistrationException {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws RegistrationException {
        if (!isValidEmail(email)) {
            throw new RegistrationException("Invalid email format.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws RegistrationException {
        if (!isValidPassword(password)) {
            throw new RegistrationException("Password must be at least 8 characters long and contain at least one digit and one uppercase letter.");
        }
        this.password = password;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*\\d.*");
    }
}

// Product Class
class Product {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Cart Class
class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) throws CartException {
        if (products.stream().anyMatch(p -> p.getProductId().equals(product.getProductId()))) {
            throw new CartException("Product already in the cart.");
        }
        products.add(product);
    }

    public void removeProduct(String productId) throws CartException {
        Product productToRemove = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
        if (productToRemove == null) {
            throw new CartException("Product not found in the cart.");
        }
        products.remove(productToRemove);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}

// Payment Interface
interface Payment {
    boolean pay(double amount) throws PaymentException;
}

// PaymentProcessor Class
class PaymentProcessor implements Payment {
    private String paymentId;
    private double amount;

    public PaymentProcessor(String paymentId, double amount) {
        this.paymentId = paymentId;
        this.amount = amount;
    }

    @Override
    public boolean pay(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Payment amount must be greater than zero.");
        }
        // Simulate payment success/failure
        boolean paymentSuccess = new Random().nextBoolean(); // Simulated payment processing
        if (!paymentSuccess) {
            throw new PaymentException("Payment failed.");
        }
        return true;
    }
}

// ECommerceSystem Class
class ECommerceSystem {
    private Map<String, User> users;
    private User loggedInUser;
    private Cart cart;

    public ECommerceSystem() {
        this.users = new HashMap<>();
        this.cart = new Cart();
    }

    public void register(String email, String password) throws RegistrationException {
        if (users.containsKey(email)) {
            throw new RegistrationException("Email is already registered.");
        }
        User user = new User(email, password);
        users.put(email, user);
    }

    public void login(String email, String password) throws LoginException {
        User user = users.get(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new LoginException("Invalid email or password.");
        }
        loggedInUser = user;
    }

    public void addToCart(Product product) throws CartException {
        cart.addProduct(product);
    }

    public void removeFromCart(String productId) throws CartException {
        cart.removeProduct(productId);
    }

    public void makePayment() throws PaymentException {
        double totalAmount = cart.getTotalAmount();
        if (totalAmount <= 0) {
            throw new PaymentException("No items in the cart to pay for.");
        }
        PaymentProcessor paymentProcessor = new PaymentProcessor(UUID.randomUUID().toString(), totalAmount);
        paymentProcessor.pay(totalAmount);
        System.out.println("Payment successful. Total amount: " + totalAmount);
    }

    public Cart getCart() {
        return cart;
    }
}

// Main Class
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        ECommerceSystem system = new ECommerceSystem();
        Scanner scanner = new Scanner(System.in);

        try {
            // User Registration
            System.out.println("Register a new user:");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            system.register(email, password);
            System.out.println("User registered successfully!");

            // User Login
            System.out.println("\nLogin:");
            System.out.print("Email: ");
            email = scanner.nextLine();
            System.out.print("Password: ");
            password = scanner.nextLine();
            system.login(email, password);
            System.out.println("Login successful!");

            // Adding Products to Cart
            Product product1 = new Product("P001", "Laptop", 1200.00);
            Product product2 = new Product("P002", "Smartphone", 800.00);
            system.addToCart(product1);
            system.addToCart(product2);
            System.out.println("Products added to cart.");

            // Displaying Cart
            System.out.println("\nCart Products:");
            for (Product product : system.getCart().getProducts()) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }

            // Remove a Product from Cart
            System.out.print("\nEnter Product ID to remove from cart: ");
            String productIdToRemove = scanner.nextLine();
            system.removeFromCart(productIdToRemove);
            System.out.println("Product removed from cart.");

            // Make Payment
            system.makePayment();

        } catch (RegistrationException | LoginException | CartException | PaymentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}