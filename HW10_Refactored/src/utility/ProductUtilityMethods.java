package utility;

import jdbc.dto.product.Product;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductUtilityMethods {
    public ArrayList<Product> getProperProducts(String category, ArrayList<Product> products) {
        return products.stream().filter(product -> product.getCategory().toString().equals(category)).collect(Collectors.toCollection(ArrayList::new));
    }

    public void printProducts(ArrayList<Product> products) {
        for (Product product : products) {
            System.out.println(product.toString() + "\n");
        }
    }
}
