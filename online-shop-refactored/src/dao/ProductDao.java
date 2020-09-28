package dao;

import dto.product.Product;
import dto.product.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {
    Connection connection = Connector.getConnection();

    public ArrayList<Product> fetchAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String query = "SELECT* FROM product";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(setProduct(resultSet));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private Product setProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setStock(resultSet.getInt("stock"));
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setBrand(resultSet.getString("brand"));
        product.setPrice(resultSet.getDouble("price"));
        product.setDescription(resultSet.getString("description"));
        ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));
        product.setCategory(category);
        return product;
    }

    public void updateProduct(Product product) {
        try {
            String query = "UPDATE product SET stock = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getStock());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}