package com.sumanth.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void addProduct(Product product) {

        String query = "INSERT INTO product(name, price, quantity) VALUES(?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product Added Successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM product";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));

                products.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public void updateProduct(Product product) {

        String query = "UPDATE product SET name=?, price=?, quantity=? WHERE id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product Updated Successfully.");
            } else {
                System.out.println("Product ID Not Found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {

        String query = "DELETE FROM product WHERE id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product Deleted Successfully.");
            } else {
                System.out.println("Product ID Not Found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}