package view;

import dto.customer.Customer;
import services.CustomerServices;

import java.util.*;

public class CustomerView {
    public CustomerServices customerServices = new CustomerServices();

    public Customer registerCustomer() {
        Customer customer = new Customer();

        System.out.println("registering a new User:\nEnter First Name:");
        customer.setFirstName(GetUserInputs.getLetteringString());

        System.out.println("Enter last Name:");
        customer.setLastName(GetUserInputs.getLetteringString());

        System.out.println("Enter your age:");
        int age = GetUserInputs.getAge();
        if (age == 0)
            return null;
        customer.setAge(age);

        System.out.println("Enter Cell Number(a real mobileNumber without 0):");
        String mobileNumber = GetUserInputs.getMobileString();
        if (checkMobileRepetition(mobileNumber)) return null;
        customer.setMobileNumber(mobileNumber);

        System.out.println("Enter Email(a real email address):");
        String email = GetUserInputs.getEmailString();
        if (checkEmailRepetition(email)) return null;
        customer.setEmail(email);

        customer.setAddress(GettingAddress.getAddress());

        System.out.println("Enter a userName:(userName can just contains letters, digits, \"-\", \"_\" and \".\")");
        customer.setUserName(GetUserInputs.getUserNameString());

        System.out.println("Enter a password: (just letters and digits are allowed, 8<=password length=<16 characters)");
        customer.setPassword(GetUserInputs.getPasswordString());

        System.out.println("-------------------------------------");
        return customer;
    }

    public Customer getSignInInfo() {
        Customer customer = new Customer();
        System.out.println("userName:");
        customer.setUserName(GetUserInputs.getUserNameString());
        System.out.println("password:");
        customer.setPassword(GetUserInputs.getPasswordString());
        return customer;
    }

    private boolean checkEmailRepetition(String email) {
        ArrayList<Customer> customers = customerServices.getCustomersList();
        for (Customer customer1 : customers) {
            if (Objects.equals(customer1.getEmail(), email)) {
                System.out.println("This email has already been registered\n");
                return true;
            }
        }
        return false;
    }

    private boolean checkMobileRepetition(String mobileNumber) {
        List<Customer> customers = customerServices.getCustomersList();
        for (Customer customer1 : customers) {
            if (Objects.equals(customer1.getMobileNumber(), mobileNumber)) {
                System.out.println("This mobileNumber has already been registered\n");
                return true;
            }
        }
        return false;
    }

    private static void sortingCustomers(ArrayList<Customer> customers) {
        Comparator<Customer> comparator = new Comparator<Customer>() {
            @Override
            public int compare(Customer customer1, Customer customer2) {
                return customer1.getAge() < customer2.getAge() ? -1 :
                        (customer1.getAge() == customer2.getAge() ? 0 : 1);
            }
        };
        customers.sort(comparator);
    }

    public void printReport() {
        ArrayList<Customer> customers = customerServices.getCustomersList();
        sortingCustomers(customers);
        System.out.println("Customers Report according to their ages: ");
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    public Customer customerSignIn() {
        Customer customer = getSignInInfo();
        customer = customerServices.findCustomer(customer);
        if (!Objects.equals(customer, null))
            System.out.println("welcome " + customer.getUserName());
        else
            System.out.println("InCorrect UserName Or Password");
        return customer;
    }

    public Customer customerSignUp() {
        Customer customer = registerCustomer();
        if (!Objects.equals(customer, null)) {
            customer = customerServices.signUp(customer);
            System.out.println("welcome " + customer.getUserName() + "\n--------------------------");
        }
        return customer;
    }
}