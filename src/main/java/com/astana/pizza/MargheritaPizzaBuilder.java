package com.astana.pizza;

/**
 * ConcreteBuilder that produces a classic Margherita pizza.
 * Pre-configures typical ingredients while still allowing customization.
 */
public class MargheritaPizzaBuilder implements PizzaBuilder {

    private Pizza.BuilderState state = new Pizza.BuilderState();
    private boolean spicyExplicitlySet = false;

    public MargheritaPizzaBuilder() {
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
        // Margherita defaults when not explicitly set
        if (state.name == null) {
            state.name = "Margherita";
        }
        if (state.dough == null) {
            state.dough = "Thin Crust";
        }
        if (state.sauce == null) {
            state.sauce = "Tomato";
        }
        if (state.cheese == null) {
            state.cheese = "Mozzarella";
        }
        if (state.toppings.isEmpty()) {
            state.toppings.add("Fresh Basil");
        }
        if (!spicyExplicitlySet) {
            state.spicy = false;   // default for Margherita style
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
