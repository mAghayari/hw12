package view;

import dao.ProductDao;
import dto.cart.Cart;
import dto.cart.CartItem;
import dto.product.Product;
import services.ProductServices;

import java.util.ArrayList;
import java.util.List;

public class CartItemView {

    public void deleteAnOrderItem(Cart cart, ArrayList<Product> products) {
        ProductServices productServices = new ProductServices();
        List<CartItem> orderedItems = cart.getCartItems();

        printOrderItems(orderedItems);

        System.out.println("Enter id of a product to delete it");
        int id = GetUserInputs.getInBoundDigitalInput(products.size());

        for (CartItem cartItem : orderedItems) {
            if (cartItem.getProduct().getId() == id) {
                productServices.getASoledItemBack(cartItem);
                orderedItems.remove(cartItem);
                cart.setOrderedItems(orderedItems);
                cart.setTotalCost(orderedItems);

                System.out.println("Item deleted");
                return;
            }
        }
        System.out.println("No such ID was found in your order\n");
    }

    public void printOrderItems(List<CartItem> orderedItems) {
        System.out.println("Your ordered:\n");

        for (CartItem cartItem : orderedItems) {
            System.out.println(cartItem.toString());
        }
    }

    public void addProductToCart(Cart cart, ProductDao productDao, ArrayList<Product> products, String category) {
        if (cart.getCartItems().size() >= 5) {
            System.out.println("There are 5 products in your orderList,\nFinalize them or remove some then continue shopping.");
        } else {
            ArrayList<Product> categoryProducts = ProductView.getProperProducts(category, products);
            ProductView.printProducts(categoryProducts);

            System.out.println("1-Choose a product\n2-Category Menu");
            int item = GetUserInputs.getInBoundDigitalInput(2);
            if (item == 1) {
                System.out.println("Enter a product Id:");
                int id = ProductView.getProperProductID(categoryProducts);
                int stock = products.get(id - 1).getStock();
                if (stock != 0) {
                    System.out.println("How much do you wanna by?(must be in stock's bound)");
                    int count = GetUserInputs.getInBoundDigitalInput(stock);

                    List<CartItem> orderedItems = cart.getCartItems();
                    boolean isInList = orderedItems.stream().anyMatch(orderItem -> orderItem.getProduct().getId() == id);
                    if (isInList) {
                        updateAnOrderItem(id, count, orderedItems);
                    } else {
                        addNewOrderItem(cart, products, id, count, orderedItems);
                    }
                    products.get(id - 1).setStock(stock - count);
                    productDao.updateProduct(products.get(id - 1));
                    System.out.println("This product added to your orderList\n");
                } else
                    System.out.println("Sorry... we're ran out of this product\n");
            }
        }
    }

    private void addNewOrderItem(Cart cart, ArrayList<Product> products, int id, int count, List<CartItem> orderedItems) {
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        cartItem.setCart(cart);
        cartItem.setProduct(products.get(id - 1));
        orderedItems.add(cartItem);
    }

    private void updateAnOrderItem(int id, int count, List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == id) {
                int index = cartItems.indexOf(cartItem);
                count += cartItems.get(index).getCount();
                cartItems.get(index).setCount(count);
                break;
            }
        }
    }
}