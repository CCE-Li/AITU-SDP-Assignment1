package com.astana.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for every pizza builder.
 *
 * <p>All construction steps are implemented exactly once here, so a concrete
 * builder only has to declare how its pizza style differs (see
 * {@link #applyStyleDefaults()}). This removes duplicated construction logic.</p>
 */
public abstract class AbstractPizzaBuilder implements PizzaBuilder {

    protected String name;
    protected String dough;
    protected String sauce;
    protected String cheese;
    protected final List<String> toppings = new ArrayList<>();
    protected boolean spicy;

    protected AbstractPizzaBuilder() {
        reset();
    }

    @Override
    public PizzaBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public PizzaBuilder setDough(String dough) {
        this.dough = dough;
        return this;
    }

    @Override
    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    @Override
    public PizzaBuilder setCheese(String cheese) {
        this.cheese = cheese;
        return this;
    }

    @Override
    public PizzaBuilder addTopping(String topping) {
        if (topping != null && !topping.isBlank()) {
            toppings.add(topping);
        }
        return this;
    }

    @Override
    public PizzaBuilder setSpicy(boolean spicy) {
        this.spicy = spicy;
        return this;
    }

    /**
     * Validates the accumulated state and assembles the immutable product.
     *
     * @throws IllegalStateException if a required field is missing or blank
     */
    @Override
    public final Pizza build() {
        validateRequiredFields();
        return new Pizza(name, dough, sauce, cheese, toppings, spicy);
    }

    /**
     * Returns the builder to its initial state, including the defaults of the
     * concrete pizza style, so the same builder can be reused safely.
     */
    @Override
    public final void reset() {
        name = null;
        dough = null;
        sauce = null;
        cheese = null;
        toppings.clear();
        spicy = false;
        applyStyleDefaults();
    }

    /**
     * Declares the characteristic defaults of a pizza style. Called on creation
     * and on every {@link #reset()} so the builder always starts from a valid
     * style baseline that the client can override step by step.
     */
    protected abstract void applyStyleDefaults();

    private void validateRequiredFields() {
        requireText(name, "name");
        requireText(dough, "dough");
        requireText(sauce, "sauce");
    }

    private static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Cannot build pizza: '" + fieldName + "' must be set and non-blank");
        }
    }
}
