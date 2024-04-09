public class Order implements Comparable<Order> {
    private String orderId;
    private char side; // 'B' for Buy, 'S' for Sell
    private int price;
    protected int quantity;

    public Order(String orderId, char side, int price, int quantity) {
        this.orderId = orderId;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
    }

    // Since the input will not be change once sent, we need not use setters

    public String getOrderId() {
        return orderId;
    }

    public char getSide() {
        return side;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public int compareTo(Order other) {
        // Compare orders based on price and arrival time
        // arrival time is considered as the order in which they are added
        if (this.price != other.price) {
            return Integer.compare(this.price, other.price);
        } else {
            // If prices are equal, older orders have higher priority
            return this.orderId.compareTo(other.orderId);
        }
    }

    @Override
    public String toString() {
        return quantity + " " + price;
    }
}