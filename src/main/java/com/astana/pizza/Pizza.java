package com.astana.pizza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Product of the Builder pattern: a fully assembled pizza.
 *
 * <p>Immutable on purpose - once a builder has produced a {@code Pizza}, its
 * state cannot be changed, so a constructed product is always consistent. The
 * constructor is package-private; the only supported way to create a pizza is
 * through a {@link PizzaBuilder}, whose {@code build()} validates the state
 * before calling it.</p>
 */
public final class Pizza {

    private final String name;
    private final String dough;
    private final String sauce;
    private final String cheese;
    private final List<String> toppings;
    private final boolean spicy;

    Pizza(String name, String dough, String sauce, String cheese,
          List<String> toppings, boolean spicy) {
        this.name = name;
        this.dough = dough;
        this.sauce = sauce;
        this.cheese = cheese;
        this.toppings = Collections.unmodifiableList(new ArrayList<>(toppings));
        this.spicy = spicy;
    }

    public String getName() {
        return name;
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Pizza)) {
            return false;
        }
        Pizza pizza = (Pizza) other;
        return spicy == pizza.spicy
                && Objects.equals(name, pizza.name)
                && Objects.equals(dough, pizza.dough)
                && Objects.equals(sauce, pizza.sauce)
                && Objects.equals(cheese, pizza.cheese)
                && Objects.equals(toppings, pizza.toppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dough, sauce, cheese, toppings, spicy);
    }

    @Override
    public String toString() {
        return "Pizza{"
                + "name='" + name + '\''
                + ", dough='" + dough + '\''
                + ", sauce='" + sauce + '\''
                + ", cheese='" + cheese + '\''
                + ", toppings=" + toppings
                + ", spicy=" + spicy
                + '}';
    }
}
