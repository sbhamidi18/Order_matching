import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        // Get iterators for bids and asks
        java.util.Iterator<Map.Entry<Integer, PriorityQueue<Order>>> bidIterator = bids.entrySet().iterator();
        java.util.Iterator<Map.Entry<Integer, PriorityQueue<Order>>> askIterator = asks.entrySet().iterator();

        // Loop until both iterators are done
        while (bidIterator.hasNext() || askIterator.hasNext()) {
            // Append one buy
            if (bidIterator.hasNext()) {
                Map.Entry<Integer, PriorityQueue<Order>> bidEntry = bidIterator.next();
                int price = bidEntry.getKey();
                PriorityQueue<Order> orders = bidEntry.getValue();
                for (Order order : orders) {
                    sb.append(String.format("%,8d %8d | ", order.getQuantity(), price));
                }
            }

            // Append one ask
            if (askIterator.hasNext()) {
                Map.Entry<Integer, PriorityQueue<Order>> askEntry = askIterator.next();
                int price = askEntry.getKey();
                PriorityQueue<Order> orders = askEntry.getValue();
                for (Order order : orders) {
                    sb.append(String.format("%8d %,8d | ", price, order.getQuantity()));
                }
                sb.append("\n");
            }
        }

        // Format trades
        sb.append("\nTrades\n");
        for (Trade trade : trades) {
            sb.append("trade ").append(trade.getAggressorOrderId()).append(",").append(trade.getRestingOrderId())
                    .append(",").append(trade.getPrice()).append(",").append(trade.getQuantity()).append("\n");
        }

        return sb.toString();
    }
}