import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class OutputProcessorAndFormatter {

    public static String formatToMD5(String output) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(output.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Generate a formatted string representation of the order book and trades
    public static String formatOrderBookAndTrades(TreeMap<Integer, PriorityQueue<Order>> bids,
            TreeMap<Integer, PriorityQueue<Order>> asks, List<Trade> trades) {
        StringBuilder sb = new StringBuilder();

        // Format bids
        sb.append("Bids (Buying)\n");
        sb.append(formatSide(bids));

        // Format asks
        sb.append("\nAsks (Selling)\n");
        sb.append(formatSide(asks));

        // Format trades
        sb.append("\nTrades\n");
        for (Trade trade : trades) {
            sb.append("trade ").append(trade.getAggressorOrderId()).append(",").append(trade.getRestingOrderId())
                    .append(",").append(trade.getPrice()).append(",").append(trade.getQuantity()).append("\n");
        }

        return sb.toString();
    }

    // Format the orders on each side of the order book
    private static String formatSide(TreeMap<Integer, PriorityQueue<Order>> side) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, PriorityQueue<Order>> entry : side.entrySet()) {
            int price = entry.getKey();
            PriorityQueue<Order> orders = entry.getValue();
            for (Order order : orders) {
                sb.append(String.format("%8d %8d | ", order.getQuantity(), price));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}