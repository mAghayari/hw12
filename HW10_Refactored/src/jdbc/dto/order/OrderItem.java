package jdbc.dto.order;

import jdbc.dto.product.Product;

public class OrderItem {
    private Order order;
    private Product product;
    private int count;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return product.toString() +
                "\ncount:" + count +
                "\n--------------------------\n";
    }
}