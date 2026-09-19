package com.astana.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PizzaBuilderTest {

    @Test
    @DisplayName("Margherita builder applies its Italian-style defaults")
    void margheritaBuilderAppliesItalianStyleDefaults() {
        Pizza pizza = new MargheritaPizzaBuilder().build();

        assertEquals("Margherita", pizza.getName());
        assertEquals("Thin Crust", pizza.getDough());
        assertEquals("Tomato", pizza.getSauce());
        assertEquals("Mozzarella", pizza.getCheese());
        assertEquals(List.of("Fresh Basil"), pizza.getToppings());
        assertFalse(pizza.isSpicy());
    }

    @Test
    @DisplayName("Pepperoni builder applies its spicy American-style defaults")
    void pepperoniBuilderAppliesSpicyStyleDefaults() {
        Pizza pizza = new PepperoniPizzaBuilder().build();

        assertEquals("Pepperoni Feast", pizza.getName());
        assertEquals("Thick Crust", pizza.getDough());
        assertEquals("Spicy Tomato", pizza.getSauce());
        assertEquals(List.of("Pepperoni", "Extra Pepperoni"), pizza.getToppings());
        assertTrue(pizza.isSpicy());
    }

    @Test
    @DisplayName("The two concrete builders produce different representations")
    void buildersProduceDifferentRepresentations() {
        Pizza margherita = new MargheritaPizzaBuilder().build();
        Pizza pepperoni = new PepperoniPizzaBuilder().build();

        assertNotEquals(margherita, pepperoni);
    }

    @Test
    @DisplayName("Fluent setters override the style defaults and chain")
    void fluentApiOverridesStyleDefaults() {
        Pizza pizza = new MargheritaPizzaBuilder()
                .setName("Custom")
                .setDough("Stuffed Crust")
                .setSauce("Pesto")
                .addTopping("Olives")
                .setSpicy(true)
                .build();

        assertEquals("Custom", pizza.getName());
        assertEquals("Stuffed Crust", pizza.getDough());
        assertEquals("Pesto", pizza.getSauce());
        assertEquals(List.of("Fresh Basil", "Olives"), pizza.getToppings());
        assertTrue(pizza.isSpicy());
    }

    @Test
    @DisplayName("Director builds the classic configuration of the current builder")
    void directorBuildsClassicConfiguration() {
        PizzaDirector director = new PizzaDirector(new MargheritaPizzaBuilder());

        Pizza pizza = director.makeClassic();

        assertEquals(new MargheritaPizzaBuilder().build(), pizza);
    }

    @Test
    @DisplayName("Director can switch the representation without changing its recipes")
    void directorSwitchesRepresentation() {
        PizzaDirector director = new PizzaDirector(new MargheritaPizzaBuilder());

        director.changeBuilder(new PepperoniPizzaBuilder());
        Pizza pizza = director.makeClassic();

        assertEquals(new PepperoniPizzaBuilder().build(), pizza);
    }

    @Test
    @DisplayName("Director builds a named recipe on top of the style defaults")
    void directorBuildsNamedRecipe() {
        PizzaDirector director = new PizzaDirector(new MargheritaPizzaBuilder());

        Pizza pizza = director.makeSpicyDeluxe();

        assertEquals("Margherita", pizza.getName());
        assertEquals(List.of("Fresh Basil", "Oregano", "Garlic"), pizza.getToppings());
        assertTrue(pizza.isSpicy());
    }

    @Test
    @DisplayName("build() rejects a blank required field")
    void buildRejectsBlankRequiredField() {
        PizzaBuilder builder = new MargheritaPizzaBuilder().setSauce("   ");

        IllegalStateException exception =
                assertThrows(IllegalStateException.class, builder::build);

        assertTrue(exception.getMessage().contains("sauce"));
    }

    @Test
    @DisplayName("build() rejects a required field cleared by the client")
    void buildRejectsMissingRequiredField() {
        PizzaBuilder builder = new MargheritaPizzaBuilder().setName(null);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    @DisplayName("reset() restores the style defaults after customisation")
    void resetRestoresStyleDefaults() {
        PizzaBuilder builder = new PepperoniPizzaBuilder()
                .setName("Custom")
                .setDough("Pan")
                .addTopping("Olives");

        builder.reset();

        assertEquals(new PepperoniPizzaBuilder().build(), builder.build());
    }

    @Test
    @DisplayName("An assembled pizza exposes an immutable topping list")
    void toppingsAreImmutable() {
        Pizza pizza = new MargheritaPizzaBuilder().build();

        assertThrows(UnsupportedOperationException.class, () -> pizza.getToppings().add("Olives"));
    }

    @Test
    @DisplayName("addTopping ignores blank or null values")
    void addToppingIgnoresBlankValues() {
        Pizza pizza = new MargheritaPizzaBuilder()
                .addTopping(null)
                .addTopping("   ")
                .build();

        assertEquals(List.of("Fresh Basil"), pizza.getToppings());
    }
}
