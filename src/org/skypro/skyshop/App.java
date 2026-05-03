package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        /** Товары (те же, что и раньше)
         *
         */
        Product bread = new SimpleProduct("Хлеб", 50);
        Product milk = new DiscountedProduct("Молоко", 80, 10);
        Product eggs = new DiscountedProduct("Яйца", 120, 5);
        Product cheese = new FixPriceProduct("Сыр");
        Product butter = new SimpleProduct("Масло", 150);
        Product chocolate = new FixPriceProduct("Шоколад");

        /**Корзина (проверка, что всё работает)
         *
         */
        ProductBasket basket = new ProductBasket();
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(eggs);
        basket.addProduct(cheese);
        basket.addProduct(butter);
        basket.addProduct(chocolate);
        /** должно вывести "Невозможно добавить продукт"
         *
         */

        System.out.println("=== Корзина ===");
        basket.printContents();
        System.out.println("Общая стоимость: " + basket.getTotalCost());
        System.out.println("Молоко есть? " + basket.contains("Молоко"));
        System.out.println();

        /**Поисковый движок
         *
         */
        SearchEngine engine = new SearchEngine(10);
        /** Добавляем все товары
         *
         */
        engine.add(bread);
        engine.add(milk);
        engine.add(eggs);
        engine.add(cheese);
        engine.add(butter);
        engine.add(chocolate);
        /** этот товар не влез в корзину, но в поиске он есть
         *
         */

        /** Создаём и добавляем статьи
         *
         */
        Article article1 = new Article("Польза хлеба", "Хлеб содержит много углеводов и полезен для энергии.");
        Article article2 = new Article("Как выбрать сыр", "Сыр бывает твёрдый и мягкий. Ищите качественный продукт.");
        Article article3 = new Article("Рецепт яичницы", "Яйца пожарьте на масле, добавьте соль.");
        engine.add(article1);
        engine.add(article2);
        engine.add(article3);

        /**Тестирование поиска
         *
         */

        System.out.println("=== Поиск: \"хлеб\" (игнорируем регистр?) ===");
        /** поиск чувствителен к регистру, используем contains как есть
         *
         */
        printSearchResults(engine.search("хлеб"));

        System.out.println("\n=== Поиск: \"Хлеб\" ===");
        printSearchResults(engine.search("Хлеб"));

        System.out.println("\n=== Поиск: \"сыр\" ===");
        printSearchResults(engine.search("сыр"));

        System.out.println("\n=== Поиск: \"яйц\" ===");
        printSearchResults(engine.search("яйц"));

        System.out.println("\n=== Поиск: \"молоко\" ===");
        printSearchResults(engine.search("молоко"));

        System.out.println("\n=== Поиск: \"шоколад\" ===");
        printSearchResults(engine.search("шоколад"));

        System.out.println("\n=== Поиск: \"Как выбрать\" ===");
        printSearchResults(engine.search("Как выбрать"));

        System.out.println("\n=== Поиск: \"несуществующий\" ===");
        printSearchResults(engine.search("несуществующий"));
    }

    private static void printSearchResults(Searchable[] results) {
        boolean found = false;
        for (Searchable item : results) {
            if (item != null) {
                System.out.println(item.getStringRepresentation());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Ничего не найдено");
        }
    }
}