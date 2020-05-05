package jdbc.dto.order;

import jdbc.dto.customer.Address;
import jdbc.dto.customer.Customer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    List<OrderItem> orderedItems = new ArrayList<>();
    private Customer customer;
    private Address address;
    private double totalCost;
    private Date date;

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

    public void setTotalCost(List<OrderItem> orderedItems) {
        totalCost = 0;
        orderedItems.forEach(orderItem -> totalCost += orderItem.getCount() * orderItem.getProduct().getPrice());
    }

    public List<OrderItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        java.util.Date now = new java.util.Date();
        date = new Date(now.getTime());
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
