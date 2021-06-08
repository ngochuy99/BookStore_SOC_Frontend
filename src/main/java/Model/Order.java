package Model;

import java.util.List;

public class Order {
    private int id;
    private String destination;
    private String shipmentFee;
    private User user;
    private List<Item> items;

    public Order() {
    }

    public Order(int id, String destination, String shipmentFee, User user, List<Item> items) {
        this.id = id;
        this.destination = destination;
        this.shipmentFee = shipmentFee;
        this.user = user;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getShipmentFee() {
        return shipmentFee;
    }

    public void setShipmentFee(String shipmentFee) {
        this.shipmentFee = shipmentFee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
