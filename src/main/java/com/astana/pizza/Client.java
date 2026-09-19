package com.astana.pizza;

/**
 * Client / Demo class.
 * Demonstrates both Director-driven construction and direct fluent usage.
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo: Pizza ===\n");

        // ---------- 1. Using Director with Margherita builder ----------
        PizzaBuilder margheritaBuilder = new MargheritaPizzaBuilder();
        PizzaDirector director = new PizzaDirector(margheritaBuilder);

        Pizza classicMargherita = director.makeClassic();
        System.out.println("1. Classic Margherita (via Director):");
        System.out.println(classicMargherita);
        System.out.println();

        // ---------- 2. Switching to Pepperoni builder ----------
        PizzaBuilder pepperoniBuilder = new PepperoniPizzaBuilder();
        director.changeBuilder(pepperoniBuilder);

        Pizza classicPepperoni = director.makeClassic();
        System.out.println("2. Classic Pepperoni (via Director):");
        System.out.println(classicPepperoni);
        System.out.println();

        // ---------- 3. Fully custom pizza using Director ----------
        Pizza custom = director.makeCustom(
                "Student Special",
                "Gluten-Free Thin",
                "Pesto",
                "Vegan Mozzarella",
                false,
                "Cherry Tomatoes", "Olives", "Mushrooms"
        );
        System.out.println("3. Custom pizza (via Director):");
        System.out.println(custom);
        System.out.println();

        // ---------- 4. Direct fluent usage without Director ----------
        Pizza fluentPizza = new PepperoniPizzaBuilder()
                .setName("Extra Hot Pepperoni")
                .setDough("Stuffed Crust")
                .setSauce("Arrabbiata")
                .setCheese("Triple Cheese")
                .addTopping("Pepperoni")
                .addTopping("Jalapenos")
                .addTopping("Chili Flakes")
                .setSpicy(true)
                .build();

        System.out.println("4. Fluent API (no Director):");
        System.out.println(fluentPizza);
        System.out.println();

        // ---------- 5. Demonstrate validation ----------
        // Force an invalid state by using a raw BuilderState path is not public,
        // so we demonstrate that build() always validates required fields.
        // (In normal usage the concrete builders supply sensible defaults.)
        System.out.println("5. Validation is enforced inside Pizza.from().");
        System.out.println("   Required fields: name, dough, sauce.");
        System.out.println("   Any missing required field throws IllegalStateException.");
    }
}
