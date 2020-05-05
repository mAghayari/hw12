package jdbc.dao;

import jdbc.dto.customer.Address;
import jdbc.dto.customer.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDao {
    private int customerCounter = 0;
    private Connection connection = Connector.getConnection();

    public boolean addCustomer(Customer customer) {
        try {
            String query = "INSERT INTO `customer`(`firstName`, `lastName`, `mobileNumber`, `email`, `address`, `userName`, `password`,`age`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getMobileNumber());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getAddress().toString());
            preparedStatement.setString(6, customer.getUserName());
            preparedStatement.setString(7, customer.getPassword());
            preparedStatement.setInt(8, customer.getAge());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            customerCounter++;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer findCustomer(String userName, String password) {
        Connection connection = Connector.getConnection();
        Customer customer = new Customer();
        try {
            String query = "SELECT* FROM `customer` WHERE userName = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer = setCustomer(resultSet);
            }
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = Connector.getConnection();
        try {
            String query = "SELECT* FROM customer";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(setCustomer(resultSet));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerCounter = customers.size();
        return customers;
    }

    private Customer setCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setFirstName(resultSet.getString("firstName"));
        customer.setLastName(resultSet.getString("lastName"));
        customer.setMobileNumber(resultSet.getString("mobileNumber"));
        customer.setEmail(resultSet.getString("email"));
        customer.setAddress(getCustomerAddress(resultSet));
        customer.setUserName(resultSet.getString("userName"));
        customer.setPassword(resultSet.getString("password"));
        customer.setAge(resultSet.getInt("age"));
        return customer;
    }

    private Address getCustomerAddress(ResultSet resultSet) throws SQLException {
        String[] address = resultSet.getString("address").split(" ");
        Address customerAddress = new Address();
        customerAddress.setProvince(address[0]);
        customerAddress.setCity(address[1]);
        customerAddress.setStreet(address[2]);
        customerAddress.setZipCode(address[3]);
        return customerAddress;
    }

    public int getCustomerCounter() {
        return customerCounter;
    }
}
