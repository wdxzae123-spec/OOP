package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;

public class App {
    public static void main(String[] args) {
        /** Создаём товары разных типов
         *
         */
        Product bread = new SimpleProduct("Хлеб", 50);
        Product milk = new DiscountedProduct("Молоко", 80, 10);   // 10% скидки
        Product eggs = new DiscountedProduct("Яйца", 120, 5);     // 5%
        Product cheese = new FixPriceProduct("Сыр");              // фиксированная цена 199
        Product butter = new SimpleProduct("Масло", 150);
        Product chocolate = new FixPriceProduct("Шоколад");       // фиксированная цена 199

        ProductBasket basket = new ProductBasket();

        /** Добавляем товары (5 + 1 лишний — проверка переполнения)
         *
         */
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(eggs);
        basket.addProduct(cheese);
        basket.addProduct(butter);
        basket.addProduct(chocolate);
        /** должно появиться "Невозможно добавить продукт"
         *
         */

        /** Печать
         *
         */
        System.out.println("=== Корзина ===");
        basket.printContents();

        /** Стоимость
         *
         */
        System.out.println("Общая стоимость: " + basket.getTotalCost());

        /** Поиск
         *
         */
        System.out.println("Молоко есть? " + basket.contains("Молоко"));
        System.out.println("Шоколад есть? " + basket.contains("Шоколад")); // нет, не влез

        /** Очистка и проверка пустой корзины
         *
         */
        basket.clear();
        System.out.println("\n=== После очистки ===");
        basket.printContents();
        System.out.println("Стоимость: " + basket.getTotalCost());
        System.out.println("Хлеб есть? " + basket.contains("Хлеб"));
    }
}