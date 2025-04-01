package abstractClassDemo;

import java.util.*;

abstract class Order {
    protected int orderId;
    protected String customerName;
    protected double amount;
    protected List<String> dishes;

    // Dish prices map
    protected static final Map<String, Double> DISH_PRICES = new HashMap<>();
    
    static {
        DISH_PRICES.put("Pizza", 15.0);
        DISH_PRICES.put("Burger", 10.0);
        DISH_PRICES.put("Pasta", 12.0);
    }

    // Constructor
    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.dishes = new ArrayList<>();
        this.amount = 0.0;
    }

    // Abstract methods
    abstract void placeOrder();

    // Concrete method for calculating total cost
    public void calculateTotal() {
        for (String dish : dishes) {
            Double price = DISH_PRICES.get(dish);
            if (price != null) {
                amount += price;
            } else {
                System.out.println("Invalid dish choice: " + dish);
            }
        }
        // Additional delivery charge for Delivery orders
       
        System.out.println("Total bill: $" + amount);
    }

    // Concrete method for generating the receipt
    public void generateReceipt() {
        System.out.println("Receipt generated for Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Ordered Dishes: " + dishes);
        System.out.println("Total Bill: $" + amount);
    }
}

class DineIn extends Order {
    private int tableNumber;

    public DineIn(int orderId, String customerName, int tableNumber) {
        super(orderId, customerName);
        this.tableNumber = tableNumber;
    }

    @Override
    void placeOrder() {
        System.out.println("Dine-in order placed by " + customerName + " at table " + tableNumber);
    }
}

class TakeAway extends Order {
    private String pickupTime;

    public TakeAway(int orderId, String customerName, String pickupTime) {
        super(orderId, customerName);
        this.pickupTime = pickupTime;
    }

    @Override
    void placeOrder() {
        System.out.println("Takeaway order placed by " + customerName + ", pickup time: " + pickupTime);
    }
}

class Delivery extends Order {
    private String deliveryAddress;

    public Delivery(int orderId, String customerName, String deliveryAddress) {
        super(orderId, customerName);
        this.deliveryAddress = deliveryAddress;
        this.amount +=5;
    }

    @Override
    void placeOrder() {
        System.out.println("Delivery order placed by " + customerName + ", delivery address: " + deliveryAddress);
    }

    @Override
    public void calculateTotal() {
        super.calculateTotal(); // Call the shared calculateTotal method
        System.out.println("Including $5 delivery charge");
    }
}

public class Restaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display menu
        System.out.println("Welcome to the Restaurant");
        System.out.println("Select Order Type:");
        System.out.println("1. Dine-in");
        System.out.println("2. Takeaway");
        System.out.println("3. Delivery");
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        Order order = null;
        switch (choice) {
            case 1:
                System.out.print("Enter Table Number: ");
                int tableNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                order = new DineIn(101, customerName, tableNumber);
                break;

            case 2:
                System.out.print("Enter Pickup Time (e.g., 6:00 PM): ");
                String pickupTime = scanner.nextLine();
                order = new TakeAway(102, customerName, pickupTime);
                break;

            case 3:
                System.out.print("Enter Delivery Address: ");
                String deliveryAddress = scanner.nextLine();
                order = new Delivery(103, customerName, deliveryAddress);
                break;

            default:
                System.out.println("Invalid choice! Exiting...");
                System.exit(0);
        }

        // Dish Selection
        System.out.println("Select Dishes:");
        System.out.println("1. Pizza ($15.0)");
        System.out.println("2. Burger ($10.0)");
        System.out.println("3. Pasta ($12.0)");
        System.out.print("Enter your choice (comma separated, e.g., Pizza,Burger): ");
        String dishChoice = scanner.nextLine();
        String[] selectedDishes = dishChoice.split(",");

        // Add dishes to the order
        for (String dish : selectedDishes) {
            dish = dish.trim();  // Remove extra spaces
            if (Order.DISH_PRICES.containsKey(dish)) {
                order.dishes.add(dish);
            } else {
                System.out.println("Invalid dish choice: " + dish);
            }
        }

        // Process the order
        order.placeOrder();
        order.calculateTotal();
        order.generateReceipt();

        scanner.close();
    }
}
