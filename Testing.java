import java.util.Scanner;

public class Testing {
        public static void main(String[] args) {
                OrderBook orderBook = new OrderBook();
                Scanner scanner = new Scanner(System.in);

                // Take input from user
                System.out.println("Enter orders in the format: orderId,side,price,quantity (e.g., 10000,B,98,25500)");
                System.out.println("Enter 'done' to finish input.");

                InputProcessor.processInput(scanner, orderBook);
                orderBook.matchOrders();

                // Format order book and trades
                String orderBookAndTradesOutput = OutputProcessorAndFormatter.formatOrderBookAndTrades(
                                orderBook.getBids(),
                                orderBook.getAsks(), orderBook.getTrades());

                // Print order book and trades
                System.out.println("Order Book and Trades:");
                System.out.println(orderBookAndTradesOutput);

                // Calculate and display MD5 hash
                String md5Hash = OutputProcessorAndFormatter.formatToMD5(orderBookAndTradesOutput);
                System.out.println("MD5 Hash: " + md5Hash);

                scanner.close();
        }
}