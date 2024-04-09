public class Trade {
    private String aggressorOrderId;
    private String restingOrderId;
    private int price;
    private int quantity;

    public Trade(String aggressorOrderId, String restingOrderId, int price, int quantity) {
        this.aggressorOrderId = aggressorOrderId;
        this.restingOrderId = restingOrderId;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getAggressorOrderId() {
        return aggressorOrderId;
    }

    public void setAggressorOrderId(String aggressorOrderId) {
        this.aggressorOrderId = aggressorOrderId;
    }

    public String getRestingOrderId() {
        return restingOrderId;
    }

    public void setRestingOrderId(String restingOrderId) {
        this.restingOrderId = restingOrderId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Trade: Aggressor Order ID = " + aggressorOrderId + ", Resting Order ID = " + restingOrderId
                + ", Price = " + price + ", Quantity = " + quantity;
    }
}