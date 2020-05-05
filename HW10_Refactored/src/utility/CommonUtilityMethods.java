package utility;

import jdbc.dao.CustomerDao;
import jdbc.dao.OrderDao;
import jdbc.dto.admin.Admin;
import jdbc.dto.customer.Customer;
import jdbc.dto.order.Order;
import jdbc.dto.order.OrderItem;
import jdbc.dto.product.Product;

import java.util.*;

public class CommonUtilityMethods {
    static Scanner scanner = new Scanner(System.in);

    public static int getInBoundDigitalInput(int bound) {
        if (bound == 0) {
            System.out.println("bound is 0");
            return 0;
        } else {
            while (true) {
                String input = scanner.next();
                if (input.matches("[0-9]+")) {
                    try {
                        if (Integer.parseInt(input) <= bound && Integer.parseInt(input) > 0)
                            return Integer.parseInt(input);
                        else {
                            System.out.println("❌ input not in bound\ntry again:");
                            scanner.nextLine();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Mismatched input...\nThe input must be numeric:");
                    }
                } else
                    System.out.println("❌ Mismatched input...\nThe input must be numeric:");
            }
        }
    }

    public static int getInteger() {
        while (true) {
            String input = scanner.next();
            if (input.matches("([1-9][0-9]+)")) {
                int number = Integer.parseInt(input);
                if (number >= 15)
                    return number;
                else {
                    System.out.println("You are too young to register on the site!");
                    break;
                }
            } else
                System.out.println("❌ Mismatched input...\njust numbers are allowed:");
        }
        return 0;
    }

    public static String getLetteringString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("(([a-zA-Z ]+[a-zA-Z]+)+|[a-zA-Z]+)")) {
                try {
                    return input;
                } catch (InputMismatchException e) {
                    System.out.println("❌ Mismatched input...\njust alphabet are allowed:");
                }
            } else
                System.out.println("❌ Mismatched input...\njust alphabet are allowed:");
        }
    }

    public static String getUserNameString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("[a-zA-z0-9._-]+")) {
                try {
                    return input;

                } catch (NumberFormatException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid userName:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid userName:");
        }
    }

    public static String getPasswordString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("[a-zA-z0-9]{8}|[a-zA-z0-9]{9}|[a-zA-z0-9]{10}|[a-zA-z0-9]{11}|[a-zA-z0-9]{12}|[a-zA-z0-9]{13}|" +
                    "[a-zA-z0-9]{14}|[a-zA-z0-9]{15}|[a-zA-z0-9]{16}")) {
                try {
                    return input;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid password:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid password:");
        }
    }

    public static void getCustomersReport(CustomerDao customerDao) {
        ArrayList<Customer> customers = customerDao.getAllCustomers();
        System.out.println("Customers Report according to their ages: ");
        Collections.sort(customers, (customer1, customer2) -> customer1.getAge() == customer2.getAge() ? 0 : customer1.getAge() > customer2.getAge() ? 1 : -1);
        customers.stream().map(Customer::toString).forEach(System.out::print);
    }

    public static Order deleteMenu(OrderUtilityMethods orderUtilityMethods, Order order, ArrayList<Product> products) {
        System.out.println("1-Delete An Item\n2-Empty Order List\n3-Main Menu");
        int editItem = CommonUtilityMethods.getInBoundDigitalInput(3);
        if (editItem == 1) {
            orderUtilityMethods.deleteAnOrderItem(order, products);
        } else if (editItem == 2) {
            order = orderUtilityMethods.cancelOrder(order);
        }
        return order;
    }

    public static void printOrderItems(OrderUtilityMethods orderUtilityMethods, Order order) {
        if (order.getOrderedItems().isEmpty())
            System.out.println("your orderList is empty\n");
        else {
            List<OrderItem> orderItems = order.getOrderedItems();
            Collections.sort(orderItems, (orderItem1, orderItem2) ->
                    orderItem1.getProduct().getPrice() == orderItem2.getProduct().getPrice() ? 0
                            : orderItem1.getProduct().getPrice() > orderItem2.getProduct().getPrice() ? 1
                            : -1);
            orderUtilityMethods.printOrderItems(order.getOrderedItems());
            System.out.println("total cost: " + orderUtilityMethods.getTotalCost(order) + "\n");
        }
    }

    public static void finalizeOrder(OrderUtilityMethods orderUtilityMethods, Order order, Customer customer) {
        order.setDate();
        OrderDao orderDao = new OrderDao();
        System.out.println("where do you wanna receive your purchase:\n1-My Account Address\n2-another Address");
        int addressItem = CommonUtilityMethods.getInBoundDigitalInput(2);
        if (addressItem == 1)
            order.setAddress(order.getCustomer().getAddress());
        else
            order.setAddress(AddressUtilityMethods.getAddress());
        orderDao.addAnOrder(order);
        Order tempOrder = order;
        customer.getOrders().add(tempOrder);
        orderUtilityMethods.emptyOrderList(order);
    }

    public static int getProperProductID(ArrayList<Product> categoryProducts) {
        while (true) {
            String input = scanner.next();
            if (input.matches("[0-9]+")) {
                int id = Integer.parseInt(input);
                for (Product p : categoryProducts) {
                    if (p.getId() == id)
                        return id;
                }
                System.out.println("❌ The id you entered is not in this category...\ntry again:");
            } else
                System.out.println("❌ Mismatched input...\nenter a valid password:");
        }
    }
}