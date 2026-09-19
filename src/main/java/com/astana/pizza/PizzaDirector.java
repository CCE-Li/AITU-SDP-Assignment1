package com.astana.pizza;

/**
 * Director: knows the sequence of steps for common pizza configurations.
 * Does not know the concrete product details - it only works with the Builder interface.
 */
public class PizzaDirector {

    private PizzaBuilder builder;

    public PizzaDirector(PizzaBuilder builder) {
        this.builder = builder;
    }

    public void changeBuilder(PizzaBuilder builder) {
        this.builder = builder;
    }

    /**
     * Builds a standard classic pizza using the current builder's defaults.
     */
    public Pizza makeClassic() {
        return builder
                .setName(null)          // let concrete builder decide the name
                .setDough(null)
                .setSauce(null)
                .setCheese(null)
                .build();
    }

    /**
     * Builds a fully customized pizza by letting the client control every step.
     * Useful when the director is still used but the client wants full freedom.
     */
    public Pizza makeCustom(String name, String dough, String sauce,
                            String cheese, boolean spicy, String... toppings) {
        builder.reset();
        builder.setName(name)
               .setDough(dough)
               .setSauce(sauce)
               .setCheese(cheese)
               .setSpicy(spicy);

        for (String topping : toppings) {
            builder.addTopping(topping);
        }
        return builder.build();
    }
}
