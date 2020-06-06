package dao;

import dto.admin.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    public Admin fetchAdmin(Admin admin) {
        Connection connection = Connector.getConnection();
        try {
            String query = "SELECT* FROM `admin` WHERE adminName = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin.getUserName());
            preparedStatement.setString(2, admin.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("adminName"));
                admin.setPassword(resultSet.getString("password"));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addAdmin(Admin admin) {
        try {
            Connection connection = Connector.getConnection();
            String query = "INSERT INTO `admin`(`adminName`, `password`) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, admin.getUserName());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}