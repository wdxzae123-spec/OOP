package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        /** Создаём несколько товаров
         *
         */
        Product bread = new Product("Хлеб", 50);
        Product milk = new Product("Молоко", 80);
        Product eggs = new Product("Яйца", 120);
        Product cheese = new Product("Сыр", 200);
        Product butter = new Product("Масло", 150);
        Product extra = new Product("Шоколад", 90); // для проверки переполнения

        /** Создаём корзину
         *
         */
        ProductBasket basket = new ProductBasket();

        /** 1. Добавление продуктов
         *
         */
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(eggs);

        /** 2. Попытка добавить продукт в заполненную корзину (заполним до 5)
         *
         */
        basket.addProduct(cheese);
        basket.addProduct(butter);
        basket.addProduct(extra);

        /** 3. Печать содержимого корзины
         *
         */
        System.out.println("=== Содержимое корзины ===");
        basket.printContents();

        /** 4. Стоимость корзины
         *
         */
        System.out.println("Стоимость корзины: " + basket.getTotalCost());

        /** 5. Поиск товара, который есть
         *
         */
        System.out.println("Есть ли Молоко? " + basket.contains("Молоко"));

        /** 6. Поиск товара, которого нет
         *
         */
        System.out.println("Есть ли Шоколад? " + basket.contains("Шоколад"));

        /** 7. Очистка корзины
         *
         */
        basket.clear();
        System.out.println("\n=== После очистки ===");

        /** 8. Печать пустой корзины
         *
         */
        basket.printContents();

        /** 9. Стоимость пустой корзины
         *
         */
        System.out.println("Стоимость пустой корзины: " + basket.getTotalCost());

        /** 10. Поиск товара в пустой корзине
         *
         */
        System.out.println("Есть ли Хлеб? " + basket.contains("Хлеб"));
    }
}