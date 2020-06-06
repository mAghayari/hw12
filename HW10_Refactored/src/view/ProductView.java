package view;

import dto.product.Product;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductView {

    public static ArrayList<Product> getProperProducts(String category, ArrayList<Product> products) {
        return products.stream().filter(product -> product.getCategory().toString().equals(category)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static void printProducts(ArrayList<Product> products) {
        for (Product product : products) {
            System.out.println(product.toString() + "\n");
        }
    }

    public static int getProperProductID(ArrayList<Product> categoryProducts) {
        while (true) {
            int id = GetUserInputs.getInteger();
            for (Product p : categoryProducts) {
                if (p.getId() == id)
                    return id;
            }
            System.out.println("❌ The id you entered is not in this category...\ntry again:");
        }
    }
}