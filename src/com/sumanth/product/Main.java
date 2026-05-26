package com.sumanth.product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductDAO productDAO = new ProductDAOImpl();

        while (true) {

            System.out.println("\n====== Product Management System ======");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            try {

                int choice = sc.nextInt();

                switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Product Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Product Quantity: ");
                    int quantity = sc.nextInt();

                    Product product = new Product(name, price, quantity);

                    productDAO.addProduct(product);

                    break;

                case 2:

                    List<Product> products = productDAO.getAllProducts();

                    if (products.isEmpty()) {
                        System.out.println("No Products Found.");
                    } else {

                        System.out.println("\n----- Product List -----");

                        for (Product p : products) {

                            System.out.println(
                                    "ID: " + p.getId() +
                                    ", Name: " + p.getName() +
                                    ", Price: " + p.getPrice() +
                                    ", Quantity: " + p.getQuantity());
                        }
                    }

                    break;

                case 3:

                    System.out.print("Enter Product ID to Update: ");
                    int updateId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter New Product Name: ");
                    String updateName = sc.nextLine();

                    System.out.print("Enter New Product Price: ");
                    double updatePrice = sc.nextDouble();

                    System.out.print("Enter New Product Quantity: ");
                    int updateQuantity = sc.nextInt();

                    Product updatedProduct =
                            new Product(updateId, updateName, updatePrice, updateQuantity);

                    productDAO.updateProduct(updatedProduct);

                    break;

                case 4:

                    System.out.print("Enter Product ID to Delete: ");
                    int deleteId = sc.nextInt();

                    productDAO.deleteProduct(deleteId);

                    break;

                case 5:

                    System.out.println("Exiting Application...");
                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice.");
                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input. Please Enter Correct Data Type.");
                sc.nextLine();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}