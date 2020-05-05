package view;

import jdbc.dao.CustomerDao;
import jdbc.dao.ProductDao;
import jdbc.dto.admin.Admin;
import jdbc.dto.product.Product;
import jdbc.dto.customer.Customer;
import jdbc.dto.order.Order;
import utility.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        OrderUtilityMethods orderUtilityMethods = new OrderUtilityMethods();
        CustomerDao customerDao = new CustomerDao();
        while (true) {
            Customer customer = null;
            Order order = new Order();
            Admin admin = new Admin();
            System.out.println("1)signIn\n2)signUp");
            int enterItem = CommonUtilityMethods.getInBoundDigitalInput(2);
            if (enterItem == 1) {
                System.out.println("1-SignIn as Manager\n2-SignIn as a User");
                int signInItem = CommonUtilityMethods.getInBoundDigitalInput(2);
                if (signInItem == 1) {
                    AdminUtilityMethods adminUtilityMethods = new AdminUtilityMethods();
                    admin = adminUtilityMethods.adminSignIn();
                } else {
                    CustomerUtilityMethods customerUtilityMethods = new CustomerUtilityMethods();
                    customer = customerUtilityMethods.customerSignIn(order);
                }
            } else {
                System.out.println("1-SignUp as Admin\n2-SignUp as a User");
                int signUpItem = CommonUtilityMethods.getInBoundDigitalInput(2);
                if (signUpItem == 1) {
                    AdminUtilityMethods adminUtilityMethods = new AdminUtilityMethods();
                    admin = adminUtilityMethods.adminSignUp();
                } else {
                    CustomerUtilityMethods customerUtilityMethods = new CustomerUtilityMethods();
                    customer = customerUtilityMethods.customerSignUp(order);
                }
            }
            if (!Objects.equals(order.getCustomer(), null)) {
                ProductDao productDao = new ProductDao();
                ArrayList<Product> products;
                mainMenu:
                while (true) {
                    System.out.println("Main Menu:");
                    System.out.println("1-Products category's List\n2-Delete Order Items\n3-Print Order\n4-Finalize Order\n5-SignOut\n6-Exit\nChoose an item:");
                    int mainMenuItem = CommonUtilityMethods.getInBoundDigitalInput(6);
                    subMenu:
                    while (true) {
                        products = productDao.getAllProducts();
                        switch (mainMenuItem) {
                            case 1:
                                System.out.println("1-Electronics\n2-Readings\n3-Shoes\n4-Main Menu");
                                int categoryItem = CommonUtilityMethods.getInBoundDigitalInput(4);
                                switch (categoryItem) {
                                    case 1:
                                        orderUtilityMethods.addAnOrderItem(order, productDao, products, "ELECTRONICS");
                                        break;
                                    case 2:
                                        orderUtilityMethods.addAnOrderItem(order, productDao, products, "READINGS");
                                        break;
                                    case 3:
                                        orderUtilityMethods.addAnOrderItem(order, productDao, products, "SHOES");
                                        break;
                                    case 4:
                                        break subMenu;
                                }
                                break;
                            case 2:
                                if (order.getOrderedItems().isEmpty())
                                    System.out.println("your orderList is empty\n");
                                else {
                                order = CommonUtilityMethods.deleteMenu(orderUtilityMethods, order, products);
                                    break subMenu;
                                }
                                break subMenu;
                            case 3:
                                CommonUtilityMethods.printOrderItems(orderUtilityMethods, order);
                                break subMenu;
                            case 4:
                                if (order.getOrderedItems().isEmpty())
                                    System.out.println("your orderList is empty\n");
                                else {
                                    CommonUtilityMethods.finalizeOrder(orderUtilityMethods, order, customer);
                                }
                                break subMenu;
                            case 5:
                                break mainMenu;
                            case 6:
                                System.exit(0);
                        }
                    }
                }
            } else if (!Objects.equals(admin.getName(), null)) {
                adminMenu:
                while (true) {
                    System.out.println("1)Reporting\n2)SignOut\n3)Exit");
                    int adminMenuItem = CommonUtilityMethods.getInBoundDigitalInput(3);
                    switch (adminMenuItem) {
                        case 1:
                            CommonUtilityMethods.getCustomersReport(customerDao);
                            break;
                        case 2:
                            break adminMenu;
                        case 3:
                            System.exit(0);
                    }
                }
            }
        }
    }
}