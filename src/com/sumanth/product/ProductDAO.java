package com.sumanth.product;

import java.util.List;

public interface ProductDAO {

    void addProduct(Product product);

    List<Product> getAllProducts();

    void updateProduct(Product product);

    void deleteProduct(int id);
}