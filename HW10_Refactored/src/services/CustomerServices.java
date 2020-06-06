package services;

import dao.CustomerDao;
import dto.customer.Customer;

import java.util.ArrayList;

public class CustomerServices {
    CustomerDao customerDao = new CustomerDao();

    public Customer signUp(Customer customer) {
        customerDao.addCustomer(customer);
        customer.setId(customerDao.getCustomerCounter());
        return customer;
    }

    public Customer findCustomer(Customer signedInCustomer) {
        return customerDao.fetchCustomer(signedInCustomer);
    }

    public ArrayList<Customer> getCustomersList() {
        return customerDao.fetchAllCustomers();
    }
}