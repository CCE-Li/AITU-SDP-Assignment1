package com.astana.pizza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Product: represents a fully constructed Pizza.
 * Immutable after creation to guarantee a consistent final state.
 */
public final class Pizza {

    private final String dough;
    private final String sauce;
    private final String cheese;
    private final List<String> toppings;
    private final boolean spicy;
    private final String name;

    private Pizza(BuilderState state) {
        this.dough = state.dough;
        this.sauce = state.sauce;
        this.cheese = state.cheese;
        this.toppings = Collections.unmodifiableList(new ArrayList<>(state.toppings));
        this.spicy = state.spicy;
        this.name = state.name;
    }

    public String getDough() {
        return dough;
    }

    public String getSauce() {
        return sauce;
    }

    public String getCheese() {
        return cheese;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", dough='" + dough + '\'' +
                ", sauce='" + sauce + '\'' +
                ", cheese='" + cheese + '\'' +
                ", toppings=" + toppings +
                ", spicy=" + spicy +
                '}';
    }

    /**
     * Internal mutable state used only by builders.
     * Keeps the Product itself clean and immutable.
     */
    static final class BuilderState {
        String dough;
        String sauce;
        String cheese;
        final List<String> toppings = new ArrayList<>();
        boolean spicy;
        String name;

        void validate() {
            if (dough == null || dough.isBlank()) {
                throw new IllegalStateException("Pizza must have a dough type");
            }
            if (sauce == null || sauce.isBlank()) {
                throw new IllegalStateException("Pizza must have a sauce");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalStateException("Pizza must have a name");
            }
        }
    }

    static Pizza from(BuilderState state) {
        state.validate();
        return new Pizza(state);
    }
}
