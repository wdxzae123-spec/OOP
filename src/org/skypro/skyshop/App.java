package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exception.BestResultNotFound;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;

public class App {
    public static void main(String[] args) {
        /** Проверки конструкторов (без изменений)
         *
         */
        System.out.println("=== Проверка создания продуктов ===");
        try {
            Product badName = new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            Product badPrice = new SimpleProduct("Товар", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            Product badDiscount = new DiscountedProduct("Товар", 200, 101);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            Product badBasePrice = new DiscountedProduct("Товар", -10, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        /** Создание продуктов
         *
         */
        Product bread = new SimpleProduct("Хлеб", 50);
        Product milk = new DiscountedProduct("Молоко", 80, 10);
        Product eggs = new DiscountedProduct("Яйца", 120, 5);
        Product cheese = new FixPriceProduct("Сыр");
        Product butter = new SimpleProduct("Масло", 150);
        Product chocolate = new FixPriceProduct("Шоколад");

        /** Корзина
         *
         */
        ProductBasket basket = new ProductBasket();
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(eggs);
        basket.addProduct(cheese);
        basket.addProduct(butter);
        basket.addProduct(chocolate); // теперь добавится без проблем

        System.out.println("\n=== Корзина до удаления ===");
        basket.printContents();

        /**Демонстрация удаления
         * Удалим существующий продукт "Хлеб"
         */
        List<Product> removed = basket.removeProductsByName("Хлеб");
        if (!removed.isEmpty()) {
            System.out.println("\nУдалённые товары:");
            for (Product p : removed) {
                System.out.println(p.getName() + ": " + p.getPrice());
            }
        }
        System.out.println("\n=== Корзина после удаления 'Хлеб' ===");
        basket.printContents();

        /** Удалим несуществующий продукт
         *
         */
        List<Product> removed2 = basket.removeProductsByName("Гречка");
        if (removed2.isEmpty()) {
            System.out.println("\nСписок удалённых пуст");
        }
        System.out.println("=== Корзина после попытки удалить 'Гречка' ===");
        basket.printContents();

        /** Поисковый движок
         *
         */
        SearchEngine engine = new SearchEngine();
        engine.add(bread);
        engine.add(milk);
        engine.add(eggs);
        engine.add(cheese);
        engine.add(butter);
        engine.add(chocolate);

        Article article1 = new Article("Польза хлеба", "Хлеб содержит много углеводов и полезен для энергии.");
        Article article2 = new Article("Как выбрать сыр", "Сыр бывает твёрдый и мягкий. Ищите качественный сыр.");
        Article article3 = new Article("Рецепт яичницы", "Яйца пожарьте на масле, добавьте соль.");
        engine.add(article1);
        engine.add(article2);
        engine.add(article3);

        System.out.println("\n=== Поиск: 'сыр' ===");
        List<Searchable> searchResults = engine.search("сыр");
        for (Searchable item : searchResults) {
            System.out.println(item.getStringRepresentation());
        }

        System.out.println("\n=== Поиск наиболее подходящего элемента ===");
        try {
            Searchable best = engine.findBestMatch("сыр");
            System.out.println("Наиболее подходящий: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            Searchable best2 = engine.findBestMatch("гречка");
            System.out.println("Наиболее подходящий: " + best2.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}