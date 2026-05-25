package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> productMap;

    public ProductBasket() {
        this.productMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        productMap.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = productMap.remove(name);
        return removed != null ? removed : new ArrayList<>();
    }

    public int getTotalCost() {
        return productMap.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void printContents() {
        if (productMap.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        // Печать всех товаров
        productMap.values().stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);

        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    private long getSpecialCount() {
        return productMap.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public boolean contains(String name) {
        List<Product> products = productMap.get(name);
        return products != null && !products.isEmpty();
    }

    public void clear() {
        productMap.clear();
    }
}