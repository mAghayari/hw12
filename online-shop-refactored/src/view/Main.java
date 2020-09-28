package view;

import dao.ProductDao;
import dto.admin.Admin;
import dto.product.Product;
import dto.customer.Customer;
import dto.cart.Cart;
import services.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        CartItemView cartItemView = new CartItemView();
        CartServices cartServices = new CartServices();
        CustomerView customerView = new CustomerView();
        AdminView adminView = new AdminView();

        while (true) {
            Customer customer;
            Cart cart = new Cart();
            Admin admin = new Admin();
            System.out.println("1)SignIn\n2)SignUp");
            int enterItem = GetUserInputs.getInBoundDigitalInput(2);
            if (enterItem == 1) {
                System.out.println("1-Admin SignIn \n2-User SignIn");
                int signInItem = GetUserInputs.getInBoundDigitalInput(2);
                if (signInItem == 1) {
                    admin = adminView.adminSignIn();
                } else {
                    customer = customerView.customerSignIn();
                    cart.setCustomer(customer);
                }
            } else {
                System.out.println("1-Admin SignUp\n2-User SignUp");
                int signUpItem = GetUserInputs.getInBoundDigitalInput(2);
                if (signUpItem == 1) {
                    admin = adminView.adminSignUp();
                } else {
                    customer = customerView.customerSignUp();
                    cart.setCustomer(customer);
                }
            }
            if (!Objects.equals(cart.getCustomer(), null)) {
                ProductDao productDao = new ProductDao();
                ArrayList<Product> products;
                mainMenu:
                while (true) {
                    System.out.println("Main Menu:");
                    System.out.println("1-Products category's List\n2-Delete Order Items\n3-Print Order\n4-Finalize Order\n5-SignOut\n6-Exit\nChoose an item:");
                    int mainMenuItem = GetUserInputs.getInBoundDigitalInput(6);
                    subMenu:
                    while (true) {
                        products = productDao.fetchAllProducts();
                        switch (mainMenuItem) {
                            case 1:
                                System.out.println("1-Electronics\n2-Readings\n3-Shoes\n4-Main Menu");
                                int categoryItem = GetUserInputs.getInBoundDigitalInput(4);
                                switch (categoryItem) {
                                    case 1:
                                        cartItemView.addProductToCart(cart, productDao, products, "ELECTRONICS");
                                        break;
                                    case 2:
                                        cartItemView.addProductToCart(cart, productDao, products, "READINGS");
                                        break;
                                    case 3:
                                        cartItemView.addProductToCart(cart, productDao, products, "SHOES");
                                        break;
                                    case 4:
                                        break subMenu;
                                }
                                break;
                            case 2:
                                if (cart.getCartItems().isEmpty())
                                    System.out.println("your orderList is empty\n");
                                else {
                                    CartView cartView = new CartView();
                                    cart = cartView.deleteOperation(cart, products);
                                }
                                break subMenu;
                            case 3:
                                CartView.printOrder(cartServices, cart);
                                break subMenu;
                            case 4:
                                if (cart.getCartItems().isEmpty())
                                    System.out.println("your orderList is empty\n");
                                else {
                                    CartView cartView = new CartView();
                                    cartView.finalizeOrder(cart);
                                }
                                break subMenu;
                            case 5:
                                break mainMenu;
                            case 6:
                                System.exit(0);
                        }
                    }
                }
            } else if (!Objects.equals(admin.getUserName(), null))
                adminView.adminMenu();
        }
    }
}