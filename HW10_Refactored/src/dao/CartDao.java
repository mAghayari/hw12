package dao;

import dto.cart.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartDao {
    public void addAnOrder(Cart cart) {
        try (Connection connection = Connector.getConnection()) {
            String query = "INSERT INTO `cart`(`customerId`, `address`, `totalCost`, `cartDate`, `cartItems`) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cart.getCustomer().getId());
            preparedStatement.setString(2, cart.getAddress().toString());
            preparedStatement.setDouble(3, cart.getTotalCost());
            preparedStatement.setTimestamp(4, cart.getDate());
            preparedStatement.setString(5, cart.getOrderedItems().toString());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}