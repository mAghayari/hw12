package dto.cart;

import dto.product.Product;

public class CartItem {
    private Cart cart;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return product.productStringForPrintOrder() +
                "\ncount:" + count +
                "\n--------------------------\n";
    }
}