package com.astana.pizza;

/**
 * Builder interface declaring the construction steps
 * that every concrete pizza builder must implement.
 * All step methods return the builder itself to support method chaining.
 */
public interface PizzaBuilder {

    PizzaBuilder setName(String name);

    PizzaBuilder setDough(String dough);

    PizzaBuilder setSauce(String sauce);

    PizzaBuilder setCheese(String cheese);

    PizzaBuilder addTopping(String topping);

    PizzaBuilder setSpicy(boolean spicy);

    /**
     * Assembles and returns the final Pizza.
     * Throws IllegalStateException when the pizza is in an invalid state.
     */
    Pizza build();

    /**
     * Resets the builder so it can be reused for another pizza.
     */
    void reset();
}
