package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    /** Константа фиксированной цены
     *
     */
    private static final int FIXED_PRICE = 199;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
