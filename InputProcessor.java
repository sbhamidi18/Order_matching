import java.util.Scanner;

public class InputProcessor {
    public static void processInput(Scanner scanner, OrderBook orderBook) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("done")) {
                break; // Stop processing input if "done" is entered
            }
            if (line.isEmpty()) {
                continue; // Skip empty lines
            }
            String[] orderData = line.split(","); // ',' is the delimiter
            if (orderData.length != 4) {
                System.err.println("Invalid input format: " + line);
                continue; // Skip invalid input lines
            }
            try {
                String orderId = orderData[0];
                char side = orderData[1].charAt(0);
                int price = Integer.parseInt(orderData[2]);
                int quantity = Integer.parseInt(orderData[3]);
                Order order = new Order(orderId, side, price, quantity);
                orderBook.addOrder(order);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input format: " + line);
                continue; // Skip invalid input lines
            }
        }
    }
}
