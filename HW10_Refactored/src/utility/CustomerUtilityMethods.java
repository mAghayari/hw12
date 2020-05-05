package utility;

import jdbc.dao.CustomerDao;
import jdbc.dto.customer.Customer;
import jdbc.dto.order.Order;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class CustomerUtilityMethods {
    Scanner scanner = new Scanner(System.in);

    public Customer registerCustomer() {
        Customer customer = new Customer();
        System.out.println("registering a new User:\nEnter First Name:");
        customer.setFirstName(CommonUtilityMethods.getLetteringString());
        System.out.println("Enter last Name:");
        customer.setLastName(CommonUtilityMethods.getLetteringString());

        System.out.println("Enter your age:");
        int age = CommonUtilityMethods.getInteger();
        if (age == 0)
            return null;
        customer.setAge(age);
        System.out.println("Enter Cell Number(a real mobileNumber without 0):");
        String mobileNumber = getMobileString();
        if (checkMobileRepetition(mobileNumber)) return null;
        customer.setMobileNumber(mobileNumber);
        System.out.println("Enter Email(a real email address):");
        String email = getEmailString();
        if (checkEmailRepetition(email)) return null;
        customer.setEmail(email);
        customer.setAddress(AddressUtilityMethods.getAddress());
        System.out.println("Enter a userName:(userName can just contains letters, digits, \"-\", \"_\" and \".\"");
        customer.setUserName(CommonUtilityMethods.getUserNameString());
        System.out.println("Enter a password: (just letters and digits are allowed, 8<=password length=<16 characters)");
        customer.setPassword(CommonUtilityMethods.getPasswordString());
        System.out.println("-------------------------------------");
        return customer;
    }

    private boolean checkEmailRepetition(String email) {
        CustomerDao customerDao = new CustomerDao();
        ArrayList<Customer> customers = customerDao.getAllCustomers();
        for (Customer customer1 : customers) {
            if (Objects.equals(customer1.getEmail(), email)) {
                System.out.println("This email has already been registered\n");
                return true;
            }
        }
        return false;
    }

    private boolean checkMobileRepetition(String mobileNumber) {
        CustomerDao customerDao = new CustomerDao();
        ArrayList<Customer> customers = customerDao.getAllCustomers();
        for (Customer customer1 : customers) {
            if (Objects.equals(customer1.getMobileNumber(), mobileNumber)) {
                System.out.println("This mobileNumber has already been registered\n");
                return true;
            }
        }
        return false;
    }

    public String getEmailString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                try {
                    return input;
                } catch (InputMismatchException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid EmailAddress:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid EmailAddress:");
        }
    }

    public String getMobileString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("9((0[1-3]|5)|(1[0-9])|(2[0-2])|(3(1|[3-9]))|(9[0-1]))[0-9]{7}")) {
                try {
                    return input;

                } catch (NumberFormatException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid Cell number:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid Cell number:");
        }
    }

    public Customer customerSignUp(Order order) {
        Customer customer;
        customer = registerCustomer();
        if (!Objects.equals(customer, null)) {
            CustomerDao customerDao = new CustomerDao();
            customerDao.addCustomer(customer);
            customer.setId(customerDao.getCustomerCounter());
            order.setCustomer(customer);
            System.out.println("welcome " + customer.getUserName());
            return customer;
        }
        return null;
    }

    public Customer customerSignIn(Order order) {
        CustomerDao customerDao = new CustomerDao();
        System.out.println("userName:");
        String userName = CommonUtilityMethods.getUserNameString();
        System.out.println("password:");
        String password = CommonUtilityMethods.getPasswordString();
        Customer signedInCustomer = customerDao.findCustomer(userName, password);
        if (Objects.equals(signedInCustomer.getUserName(), userName) && Objects.equals(signedInCustomer.getPassword(), password)) {
            System.out.println("welcome " + userName);
            order.setCustomer(signedInCustomer);
            return signedInCustomer;
        } else {
            System.out.println("InCorrect UserName Or Password");
            return null;
        }
    }
}