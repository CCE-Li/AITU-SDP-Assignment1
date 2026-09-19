package com.astana.pizza;

/**
 * Client: exercises the builders and prints the resulting products, both via
 * the {@link PizzaDirector} and directly through the fluent API.
 */
public final class Client {

    private static final String SEPARATOR = "----------------------------------------";

    private Client() {
    }

    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo: Pizza ===");

        showClassicMargherita();
        showClassicPepperoni();
        showSpicyDeluxeVariant();
        showFluentUsageWithoutDirector();
        showValidation();
    }

    private static void showClassicMargherita() {
        PizzaDirector director = new PizzaDirector(new MargheritaPizzaBuilder());
        printProduct("1. Classic Margherita (via Director)", director.makeClassic());
    }

    private static void showClassicPepperoni() {
        PizzaDirector director = new PizzaDirector(new PepperoniPizzaBuilder());
        printProduct("2. Classic Pepperoni (via Director)", director.makeClassic());
    }

    private static void showSpicyDeluxeVariant() {
        PizzaDirector director = new PizzaDirector(new PepperoniPizzaBuilder());
        Pizza deluxe = director.makeSpicyDeluxe();
        printProduct("3. Spicy Deluxe (named recipe, same Director)", deluxe);
    }

    private static void showFluentUsageWithoutDirector() {
        Pizza pizza = new PepperoniPizzaBuilder()
                .setName("Extra Hot Pepperoni")
                .setDough("Stuffed Crust")
                .setSauce("Arrabbiata")
                .setCheese("Triple Cheese")
                .addTopping("Jalapenos")
                .addTopping("Chili Flakes")
                .setSpicy(true)
                .build();
        printProduct("4. Fluent API (no Director)", pizza);
    }

    private static void showValidation() {
        System.out.println();
        System.out.println("5. Validation on build()");
        try {
            new MargheritaPizzaBuilder().setName("  ").build();
            System.out.println("   ERROR: an invalid pizza was accepted");
        } catch (IllegalStateException exception) {
            System.out.println("   Rejected as expected -> " + exception.getMessage());
        }
    }

    private static void printProduct(String title, Pizza pizza) {
        System.out.println();
        System.out.println(title);
        System.out.println(SEPARATOR);
        System.out.println(pizza);
    }
}
