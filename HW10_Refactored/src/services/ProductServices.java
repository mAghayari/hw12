package services;

import dao.ProductDao;
import dto.cart.CartItem;
import dto.product.Product;

import java.util.List;

public class ProductServices {

    public void getASoledItemBack(CartItem cartItem) {
        ProductDao productDao = new ProductDao();
        Product product = cartItem.getProduct();

        product.setStock(cartItem.getCount() + product.getStock());
        productDao.updateProduct(product);
    }

    public void getAllSoledItemsBack(List<CartItem> orderedItems) {
        for (CartItem cartItem : orderedItems) {
            getASoledItemBack(cartItem);
        }
    }
}