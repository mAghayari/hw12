package jdbc.dao;

import jdbc.dto.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDao {
    private int orderCounter = 0;

    public void addAnOrder(Order order) {
        try {
            Connection connection = Connector.getConnection();
            String query = "INSERT INTO `order`(`customerID`, `address`, `totalCost`, `date`, `orderItems`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, order.getCustomer().getId());
            preparedStatement.setString(2, order.getAddress().toString());
            preparedStatement.setDouble(3, order.getTotalCost());
            preparedStatement.setDate(4, order.getDate());
            preparedStatement.setString(5, order.getOrderedItems().toString());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
            orderCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getOrderCounter() {
        return orderCounter;
    }
}