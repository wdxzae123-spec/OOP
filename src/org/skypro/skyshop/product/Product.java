package org.skypro.skyshop.product;

public class Product {
    private final String name;
    private final int price;

    /** стоимость в целых единицах (например, рубли)
     *
     * @param name
     * @param price
     */

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }}
