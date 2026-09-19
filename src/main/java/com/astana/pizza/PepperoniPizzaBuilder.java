package com.astana.pizza;

/**
 * Concrete builder for an American-style spicy Pepperoni pizza.
 * Produces a deliberately different representation from
 * {@link MargheritaPizzaBuilder}: thicker dough, spicy sauce and meat toppings.
 */
public final class PepperoniPizzaBuilder extends AbstractPizzaBuilder {

    private static final String STYLE_NAME = "Pepperoni Feast";
    private static final String STYLE_DOUGH = "Thick Crust";
    private static final String STYLE_SAUCE = "Spicy Tomato";
    private static final String STYLE_CHEESE = "Mozzarella + Cheddar";
    private static final String SIGNATURE_TOPPING = "Pepperoni";
    private static final String EXTRA_TOPPING = "Extra Pepperoni";

    @Override
    protected void applyStyleDefaults() {
        name = STYLE_NAME;
        dough = STYLE_DOUGH;
        sauce = STYLE_SAUCE;
        cheese = STYLE_CHEESE;
        toppings.add(SIGNATURE_TOPPING);
        toppings.add(EXTRA_TOPPING);
        spicy = true;
    }
}
