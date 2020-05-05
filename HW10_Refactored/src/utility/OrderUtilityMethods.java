package utility;

import com.sun.source.tree.BreakTree;
import jdbc.dao.ProductDao;
import jdbc.dto.product.Product;
import jdbc.dto.order.Order;
import jdbc.dto.order.OrderItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class OrderUtilityMethods {
    ProductUtilityMethods productUtilityMethods = new ProductUtilityMethods();

    public void emptyOrderList(Order order) {
        List<OrderItem> orderedItems = order.getOrderedItems();
        orderedItems = Collections.synchronizedList(orderedItems);
        synchronized (orderedItems) {
            orderedItems.forEach(orderedItems::remove);
        }
        order.setOrderedItems(orderedItems);
    }

    public Order cancelOrder(Order order) {
        List<OrderItem> orderedItems = order.getOrderedItems();
        getAllSoledItemsBack(orderedItems);
        orderedItems = Collections.synchronizedList(orderedItems);
        synchronized (orderedItems) {
            orderedItems.forEach(orderedItems::remove);
        }
        order.setOrderedItems(orderedItems);
        return order;
    }

    public void deleteAnOrderItem(Order order, ArrayList<Product> products) {
        List<OrderItem> orderedItems = order.getOrderedItems();
        printOrderItems(orderedItems);
        System.out.println("enter id of a product to delete it");
        int id = CommonUtilityMethods.getInBoundDigitalInput(products.size());
        for (OrderItem orderItem : orderedItems) {
            if (orderItem.getProduct().getId() == id) {
                getASoledItemBack(orderItem);
                orderedItems.remove(orderItem);
                order.setOrderedItems(orderedItems);
                order.setTotalCost(orderedItems);
                System.out.println("Item deleted");
                return;
            }
        }
        System.out.println("No such ID was found in your order\n");
    }

    private void getASoledItemBack(OrderItem orderItem) {
        ProductDao productDao = new ProductDao();
        Product product = orderItem.getProduct();
        product.setStock(orderItem.getCount() + product.getStock());
        productDao.updateProduct(product);
    }

    private void getAllSoledItemsBack(List<OrderItem> orderedItems) {
        orderedItems.forEach(this::getASoledItemBack);
    }

    public void printOrderItems(List<OrderItem> orderedItems) {
        System.out.println("your ordered:\n");
        orderedItems.stream().map(OrderItem::toString).forEach(System.out::print);
    }

    public double getTotalCost(Order order) {
        order.setTotalCost(order.getOrderedItems());
        return order.getTotalCost();
    }

    public void addAnOrderItem(Order order, ProductDao productDao, ArrayList<Product> products, String category) {
        if (order.getOrderedItems().size() >= 5) {
            System.out.println("there are 5 products in your orderList,\nfinalize them or remove some then continue shopping.");
        } else {
            ArrayList<Product> categoryProducts = productUtilityMethods.getProperProducts(category, products);
            productUtilityMethods.printProducts(categoryProducts);
            System.out.println("1-choose a product\n2-category Menu");
            int item = CommonUtilityMethods.getInBoundDigitalInput(2);
            if (item == 1) {
                System.out.println("enter a product Id:");
                int id = CommonUtilityMethods.getProperProductID(categoryProducts);
                int stock = products.get(id - 1).getStock();
                if (stock != 0) {
                    System.out.println("how much do you wanna by?(must be in stock's bound)");
                    int count = CommonUtilityMethods.getInBoundDigitalInput(stock);
                    int index;
                    List<OrderItem> orderedItems = order.getOrderedItems();
                    boolean isInList = orderedItems.stream().anyMatch(orderItem -> orderItem.getProduct().getId() == id);
                    if (isInList) {
                        index = orderedItems.indexOf(id);
                        count += orderedItems.get(index).getCount();
                        orderedItems.get(index).setCount(count);
                    } else {
                        OrderItem orderItem = new OrderItem();
                        setOrderItem(order, products.get(id - 1), count, orderItem);
                        orderedItems.add(orderItem);
                    }
                    products.get(id - 1).setStock(stock - count);
                    productDao.updateProduct(products.get(id - 1));
                    System.out.println("this product added to your orderList\n");
                } else
                    System.out.println("ran out of this product\n");
            }
        }
    }

    private void setOrderItem(Order order, Product product, int count, OrderItem orderItem) {
        orderItem.setCount(count);
        orderItem.setOrder(order);
        orderItem.setProduct(product);
    }
}