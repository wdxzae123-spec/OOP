package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.Product;
public class ProductBasket {
    private final Product[] products; // хранилище ровно на 5 товаров

    public ProductBasket() {
        this.products = new Product[5];
    }

    /**
     * Добавление продукта в корзину.
     * Если свободного места нет, выводит сообщение в консоль.
     */
    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return;
            }
        }
        System.out.println("Невозможно добавить продукт");
    }

    /**
     * Возвращает общую стоимость всех товаров в корзине.
     */
    public int getTotalCost() {
        int total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getPrice();
            }
        }
        return total;
    }

    /**
     * Печатает содержимое корзины в консоль.
     * Если корзина пуста — выводит "в корзине пусто".
     */
    public void printContents() {
        if (isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        for (Product product : products) {
            if (product != null) {
                System.out.println(product.getName() + ": " + product.getPrice());
            }
        }
        System.out.println("Итого: " + getTotalCost());
    }

    /**
     * Проверяет наличие товара по имени.
     * @param name имя товара
     * @return true, если товар с таким именем есть в корзине
     */
    public boolean contains(String name) {
        for (Product product : products) {
            if (product != null && product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Очищает корзину (присваивает null всем элементам массива).
     */
    public void clear() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
    }

    // Вспомогательный приватный метод для проверки пустоты корзины
    private boolean isEmpty() {
        for (Product product : products) {
            if (product != null) {
                return false;
            }
        }
        return true;
    }
}