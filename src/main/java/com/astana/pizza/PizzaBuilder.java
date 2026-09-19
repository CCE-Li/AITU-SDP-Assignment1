package com.astana.pizza;

/**
 * Builder: declares the step-by-step construction steps shared by every pizza
 * builder. Each step returns the builder itself, which enables the fluent
 * (method-chaining) API expected by the client.
 */
public interface PizzaBuilder {

    PizzaBuilder setName(String name);

    PizzaBuilder setDough(String dough);

    PizzaBuilder setSauce(String sauce);

    PizzaBuilder setCheese(String cheese);

    /** Adds one topping; blank or {@code null} values are ignored. */
    PizzaBuilder addTopping(String topping);

    PizzaBuilder setSpicy(boolean spicy);

    /**
     * Assembles and returns the final product.
     *
     * @throws IllegalStateException if a required field is missing or blank
     */
    Pizza build();

    /** Returns the builder to its initial (style-default) state. */
    void reset();
}
