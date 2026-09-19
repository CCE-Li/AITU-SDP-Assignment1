package com.astana.pizza;

/**
 * Concrete builder for a classic Neapolitan Margherita.
 * Only the style-specific defaults live here; all construction mechanics are
 * inherited from {@link AbstractPizzaBuilder}.
 */
public final class MargheritaPizzaBuilder extends AbstractPizzaBuilder {

    private static final String STYLE_NAME = "Margherita";
    private static final String STYLE_DOUGH = "Thin Crust";
    private static final String STYLE_SAUCE = "Tomato";
    private static final String STYLE_CHEESE = "Mozzarella";
    private static final String SIGNATURE_TOPPING = "Fresh Basil";

    @Override
    protected void applyStyleDefaults() {
        name = STYLE_NAME;
        dough = STYLE_DOUGH;
        sauce = STYLE_SAUCE;
        cheese = STYLE_CHEESE;
        toppings.add(SIGNATURE_TOPPING);
        spicy = false;
    }
}
