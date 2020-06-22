package dto.cart;

import dto.address.Address;
import dto.customer.Customer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int orderID;
    List<CartItem> orderedItems = new ArrayList<>();
    private Customer customer;
    private Address address;
    private double totalCost;
    private Timestamp date;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(List<CartItem> orderedItems) {
        totalCost = 0;
        orderedItems.forEach(orderItem -> totalCost += orderItem.getCount() * orderItem.getProduct().getPrice());
    }

    public List<CartItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<CartItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate() {
        java.util.Date now = new java.util.Date();
        date = new Timestamp(now.getTime());
    }

    @Override
    public String toString() {
        return "Order " + orderID +
                "\ncustomer: " + customer +
                "\naddress: " + address +
                "\ntotalCost: " + totalCost +
                "\nOrderDate: " + date +
                "\norderedItems: " + orderedItems.toString() +
                "\n";
    }
}