package org.skypro.skyshop.product;

public abstract class Product {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** Абстрактный метод – цена зависит от типа товара
     *
     */
    public abstract int getPrice();

    /** Метод, определяющий, является ли товар специальным.
     *
     */

    /** По умолчанию – нет. Переопределяется в подклассах.
     *
     */
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        /**
         Базовая реализация (при необходимости может быть переопределена в наследниках)
         */
        return name + ": " + getPrice();
    }
}

