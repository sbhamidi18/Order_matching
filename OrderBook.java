import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

public class OrderBook {
    private TreeMap<Integer, PriorityQueue<Order>> bids;
    private TreeMap<Integer, PriorityQueue<Order>> asks;
    private List<Trade> trades;

    public OrderBook() {
        this.bids = new TreeMap<>(Comparator.reverseOrder()); // highest buy goes first
        this.asks = new TreeMap<>();
        this.trades = new ArrayList<>();
    }

    public void addOrder(Order order) {
        int price = order.getPrice();
        int quantity = order.getQuantity();

        if (order.getSide() == 'B') {
            bids.computeIfAbsent(price, k -> new PriorityQueue<>()).add(order);
        } else if (order.getSide() == 'S') {
            // Check if the order already exists in asks before adding
            if (!asksContainsOrder(order)) {
                asks.computeIfAbsent(price, k -> new PriorityQueue<>()).add(order);
            }
        } else {
            System.err.println("Invalid order side: " + order.getSide());
        }

        // Debug output to verify order addition
        System.out.println("Added order: " + quantity + " " + price);
    }

    // Prevent duplicate orders from being added to the "asks" side of the order
    // book
    private boolean asksContainsOrder(Order order) {
        int price = order.getPrice();
        PriorityQueue<Order> orders = asks.get(price);
        if (orders != null) {
            for (Order existingOrder : orders) {
                if (existingOrder.getOrderId().equals(order.getOrderId())) {
                    return true; // Order already exists
                }
            }
        }
        return false; // Order does not exist
    }

    public void matchOrders() {
        trades.clear(); // Clear the trades list before matching orders

        for (Map.Entry<Integer, PriorityQueue<Order>> bidEntry : bids.entrySet()) {
            int bidPrice = bidEntry.getKey();
            PriorityQueue<Order> bidOrders = bidEntry.getValue();

            for (Map.Entry<Integer, PriorityQueue<Order>> askEntry : asks.entrySet()) {
                int askPrice = askEntry.getKey();
                PriorityQueue<Order> askOrders = askEntry.getValue();

                while (!askOrders.isEmpty() && !bidOrders.isEmpty() && askPrice <= bidPrice) {
                    Order bidOrder = bidOrders.peek();
                    Order askOrder = askOrders.peek();

                    // Check if the ask price is less than or equal to the bid price
                    if (askOrder.getPrice() <= bidPrice) {
                        // Trade occurs
                        int tradePrice = askOrder.getPrice();
                        int tradeQuantity = Math.min(bidOrder.getQuantity(), askOrder.getQuantity());
                        trades.add(new Trade(bidOrder.getOrderId(), askOrder.getOrderId(), tradePrice, tradeQuantity));

                        // Update order quantities
                        bidOrder.reduceQuantity(tradeQuantity);
                        askOrder.reduceQuantity(tradeQuantity);

                        // Remove orders if fully filled
                        if (bidOrder.getQuantity() == 0) {
                            bidOrders.poll();
                        }
                        if (askOrder.getQuantity() == 0) {
                            askOrders.poll();
                        }
                    } else {
                        break; // No further matching possible at this ask price
                    }
                }
            }
        }
    }

    public TreeMap<Integer, PriorityQueue<Order>> getBids() {
        return bids;
    }

    public TreeMap<Integer, PriorityQueue<Order>> getAsks() {
        return asks;
    }

    public List<Trade> getTrades() {
        return trades;
    }
}