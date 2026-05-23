package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    /** Ключ – имя продукта, значение – список продуктов с этим именем
     *
     */
    private final Map<String, List<Product>> productMap;

    public ProductBasket() {
        this.productMap = new HashMap<>();
    }

    /**
     * Добавляет продукт в корзину.
     */
    public void addProduct(Product product) {
        String name = product.getName();
        productMap.computeIfAbsent(name, k -> new ArrayList<>()).add(product);
    }

    /**
     * Удаляет все продукты с указанным именем.
     * @return список удалённых продуктов (может быть пустым)
     */
    public List<Product> removeProductsByName(String name) {
        List<Product> removed = productMap.remove(name);
        if (removed == null) {
            return new ArrayList<>(); // возвращаем пустой список, если товара с таким именем не было
        }
        return removed;
    }

    /**
     * Возвращает общую стоимость всех товаров в корзине.
     */
    public int getTotalCost() {
        int total = 0;
        for (List<Product> productList : productMap.values()) {
            for (Product p : productList) {
                total += p.getPrice();
            }
        }
        return total;
    }

    /**
     * Выводит содержимое корзины в консоль.
     */
    public void printContents() {
        if (productMap.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        int specialCount = 0;
        for (List<Product> productList : productMap.values()) {
            for (Product p : productList) {
                System.out.println(p); // использует toString() товара
                if (p.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }

    /**
     * Проверяет, есть ли в корзине товар с заданным именем.
     */
    public boolean contains(String name) {
        List<Product> products = productMap.get(name);
        return products != null && !products.isEmpty();
    }

    /**
     * Полностью очищает корзину.
     */
    public void clear() {
        productMap.clear();
    }
}