package org.skypro.skyshop;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exception.BestResultNotFound;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        /** Проверки конструкторов товаров (демонстрация исключений)
         *
         */
        System.out.println("=== Проверка создания продуктов ===");
        /** Некорректное имя
         *
         */
        try {
            Product badName = new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        /** Цена <= 0
         *
         */
        try {
            Product badPrice = new SimpleProduct("Товар", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        /** Скидка вне диапазона
         *
         */
        try {
            Product badDiscount = new DiscountedProduct("Товар", 200, 101);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        /** Базовая цена <= 0 для скидочного товара
         *
         */
        try {
            Product badBasePrice = new DiscountedProduct("Товар", -10, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        /** Создание корректных товаров и корзины (как раньше)
         *
         */
        Product bread = new SimpleProduct("Хлеб", 50);
        Product milk = new DiscountedProduct("Молоко", 80, 10);
        Product eggs = new DiscountedProduct("Яйца", 120, 5);
        Product cheese = new FixPriceProduct("Сыр");
        Product butter = new SimpleProduct("Масло", 150);
        Product chocolate = new FixPriceProduct("Шоколад");

        ProductBasket basket = new ProductBasket();
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(eggs);
        basket.addProduct(cheese);
        basket.addProduct(butter);
        basket.addProduct(chocolate);
        /** переполнение корзины
         *
         */

        System.out.println("\n=== Корзина ===");
        basket.printContents();

        /** Настройка поискового движка
         *
         */
        SearchEngine engine = new SearchEngine(10);
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

        /** Демонстрация findBestMatch
         *
         */
        System.out.println("\n=== Поиск наиболее подходящего элемента ===");
        try {
            Searchable best = engine.findBestMatch("сыр");
            System.out.println("Наиболее подходящий: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            /** Запрос, которого точно нет
             *
             */
            Searchable best2 = engine.findBestMatch("гречка");
            System.out.println("Наиболее подходящий: " + best2.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}