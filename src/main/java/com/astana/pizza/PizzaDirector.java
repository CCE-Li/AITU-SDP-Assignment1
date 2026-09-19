package com.astana.pizza;

/**
 * Director: encapsulates the sequence of build steps for known, reusable
 * configurations. It depends only on the {@link PizzaBuilder} interface, so it
 * never knows which concrete representation it is constructing.
 *
 * <p>Fully bespoke pizzas are intentionally not built here - that is what the
 * fluent builder API is for ({@code builder.setX(...).build()}), because only
 * the client knows the one-off sequence.</p>
 */
public class PizzaDirector {

    private static final String EXTRA_TOPPING_ONE = "Oregano";
    private static final String EXTRA_TOPPING_TWO = "Garlic";

    private PizzaBuilder builder;

    public PizzaDirector(PizzaBuilder builder) {
        this.builder = builder;
    }

    /** Swaps the concrete builder while keeping the same recipes. */
    public void changeBuilder(PizzaBuilder builder) {
        this.builder = builder;
    }

    /**
     * Builds the plain configuration of whichever builder is currently set, by
     * relying on that builder's own style defaults.
     */
    public Pizza makeClassic() {
        builder.reset();
        return builder.build();
    }

    /**
     * Builds a richer "spicy deluxe" configuration on top of the current
     * builder's defaults: the same base pizza plus two extra toppings, made
     * spicy.
     */
    public Pizza makeSpicyDeluxe() {
        builder.reset();
        builder.setSpicy(true)
                .addTopping(EXTRA_TOPPING_ONE)
                .addTopping(EXTRA_TOPPING_TWO);
        return builder.build();
    }
}
