package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductBasket {
    private final List<Product> products;

    public ProductBasket() {
        this.products = new ArrayList<>();
    }

    /**
     * Добавляет продукт в корзину. Ограничений по количеству больше нет.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Удаляет все продукты с указанным именем из корзины.
     * @param name имя товара
     * @return список удалённых продуктов (может быть пустым)
     */
    public List<Product> removeProductsByName(String name) {
        List<Product> removed = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removed.add(product);
                iterator.remove();
            }
        }
        return removed;
    }

    public int getTotalCost() {
        int total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        int specialCount = 0;
        for (Product p : products) {
            System.out.println(p);   // toString() товара
            if (p.isSpecial()) {
                specialCount++;
            }
        }
        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean contains(String name) {
        for (Product p : products) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        products.clear();
    }
}