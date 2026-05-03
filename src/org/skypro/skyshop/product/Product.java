package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSearchTerm() {
        /** Поиск по имени товара
         *
         */
        return getName();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    /** getStringRepresentation() наследуется из интерфейса (default)
     *
     */

    /** Абстрактные и виртуальные методы товара
     *
     */
    public abstract int getPrice();
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        /** Оставляем прежнюю логику для вывода в корзине
         * (у подклассов она переопределена)
         *
        */
        return name + ": " + getPrice();
    }
}

