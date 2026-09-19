package com.astana.pizza;

/**
 * ConcreteBuilder that produces a spicy Pepperoni pizza.
 * Different representation from Margherita: thicker dough, spicy sauce, meat toppings.
 */
public class PepperoniPizzaBuilder implements PizzaBuilder {

    private Pizza.BuilderState state = new Pizza.BuilderState();
    private boolean spicyExplicitlySet = false;

    public PepperoniPizzaBuilder() {
        reset();
    }

    @Override
    public PizzaBuilder setName(String name) {
        state.name = name;
        return this;
    }

    @Override
    public PizzaBuilder setDough(String dough) {
        state.dough = dough;
        return this;
    }

    @Override
    public PizzaBuilder setSauce(String sauce) {
        state.sauce = sauce;
        return this;
    }

    @Override
    public PizzaBuilder setCheese(String cheese) {
        state.cheese = cheese;
        return this;
    }

    @Override
    public PizzaBuilder addTopping(String topping) {
        if (topping != null && !topping.isBlank()) {
            state.toppings.add(topping);
        }
        return this;
    }

    @Override
    public PizzaBuilder setSpicy(boolean spicy) {
        state.spicy = spicy;
        spicyExplicitlySet = true;
        return this;
    }

    @Override
    public Pizza build() {
        // Pepperoni defaults when not explicitly set
        if (state.name == null) {
            state.name = "Pepperoni Feast";
        }
        if (state.dough == null) {
            state.dough = "Thick Crust";
        }
        if (state.sauce == null) {
            state.sauce = "Spicy Tomato";
        }
        if (state.cheese == null) {
            state.cheese = "Mozzarella + Cheddar";
        }
        if (state.toppings.isEmpty()) {
            state.toppings.add("Pepperoni");
            state.toppings.add("Extra Pepperoni");
        }
        if (!spicyExplicitlySet) {
            state.spicy = true;   // default for Pepperoni style
        }

        Pizza pizza = Pizza.from(state);
        reset();
        return pizza;
    }

    @Override
    public void reset() {
        state = new Pizza.BuilderState();
        spicyExplicitlySet = false;
    }
}
