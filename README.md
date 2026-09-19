# Builder Pattern — Pizza

**Course:** ShP-2216 – Software Design Patterns
**Assignment:** #1 – Builder Pattern
**Language:** Java 17 · **Build:** Maven · **Tests:** JUnit 5

## What is this project?

This project demonstrates the **Builder** creational design pattern by assembling
different kinds of pizzas step by step through a fluent API.

Two concrete builders produce meaningfully different representations:

| Builder | Style | Characteristics |
|---------|-------|-----------------|
| `MargheritaPizzaBuilder` | Classic Italian | Thin crust, tomato sauce, mozzarella, fresh basil, not spicy |
| `PepperoniPizzaBuilder` | American spicy | Thick crust, spicy tomato sauce, cheddar blend, double pepperoni, spicy |

A `PizzaDirector` provides reusable, named configurations, while the fluent API still
allows full customisation.

## Pattern roles

| Role | Type |
|------|------|
| Product | `Pizza` (immutable) |
| Builder | `PizzaBuilder` (interface) |
| Shared construction logic | `AbstractPizzaBuilder` (abstract, template methods) |
| ConcreteBuilder | `MargheritaPizzaBuilder`, `PepperoniPizzaBuilder` |
| Director | `PizzaDirector` |
| Client | `Client` |

## Project structure

```
builder-pattern-pizza/
├── pom.xml
├── README.md
├── REPORT.md                      # assignment report (incl. Clean Code section)
├── REPORT.pdf                     # rendered report, ready to submit
├── docs/
│   ├── uml.puml                   # PlantUML class diagram (source)
│   ├── uml.png                    # rendered diagram
│   └── uml.svg                    # rendered diagram (vector)
└── src/
    ├── main/java/com/astana/pizza/
    │   ├── Pizza.java                  # Product (immutable)
    │   ├── PizzaBuilder.java           # Builder interface
    │   ├── AbstractPizzaBuilder.java   # Shared steps (build/reset are final)
    │   ├── MargheritaPizzaBuilder.java # ConcreteBuilder 1
    │   ├── PepperoniPizzaBuilder.java  # ConcreteBuilder 2
    │   ├── PizzaDirector.java          # Director
    │   └── Client.java                 # Demo / main
    └── test/java/com/astana/pizza/
        └── PizzaBuilderTest.java       # JUnit 5 tests
```

## How to build and run

Requires JDK 17+ and Maven.

```bash
# compile + run the demo
mvn compile exec:java

# run the test suite
mvn test

# package a jar
mvn package
```

Or open the folder in IntelliJ IDEA and run `com.astana.pizza.Client`.

## How to build each representation

### 1. Via the Director (reusable recipes)

```java
PizzaDirector director = new PizzaDirector(new MargheritaPizzaBuilder());

Pizza classic = director.makeClassic();       // style defaults
Pizza deluxe  = director.makeSpicyDeluxe();   // defaults + extra toppings, spicy
```

Swapping the representation does not change the director:

```java
director.changeBuilder(new PepperoniPizzaBuilder());
Pizza pepperoni = director.makeClassic();
```

### 2. Via the fluent API (full control)

```java
Pizza pizza = new PepperoniPizzaBuilder()
        .setName("Extra Hot Pepperoni")
        .setDough("Stuffed Crust")
        .setSauce("Arrabbiata")
        .setCheese("Triple Cheese")
        .addTopping("Jalapenos")
        .addTopping("Chili Flakes")
        .setSpicy(true)
        .build();
```

### 3. Validation

`build()` rejects incomplete products with a clear message:

```java
new MargheritaPizzaBuilder().setName("").build();
// IllegalStateException: Cannot build pizza: 'name' must be set and non-blank
```

## Design decisions

- **Immutable product** — a built `Pizza` cannot be changed; the topping list is copied
  defensively and exposed as unmodifiable.
- **No duplicated construction logic** — every step is implemented once in
  `AbstractPizzaBuilder`; concrete builders only override `applyStyleDefaults()`, and
  `build()`/`reset()` are `final`.
- **Method chaining** — every setter returns the builder (`this`).
- **Validated construction** — `build()` validates the state and throws
  `IllegalStateException` naming the missing field.
- **Director owns known recipes only** — it never proxies arbitrary client input; bespoke
  pizzas use the fluent API directly.
- **No magic values** — fixed strings and defaults are named constants.

## Clean Code principles applied

See [`REPORT.md`](REPORT.md) §3 for annotated before/after excerpts.

1. No duplicated construction logic between builders
2. Meaningful, intention-revealing names
3. Small methods that each do one thing
4. Validated construction with a clear exception
5. No magic numbers or strings
6. Immutable product with a defensive copy
7. Program to an interface, not an implementation

## UML

![UML class diagram](docs/uml.png)

Source: [`docs/uml.puml`](docs/uml.puml) (PlantUML) · rendered: [`docs/uml.png`](docs/uml.png),
[`docs/uml.svg`](docs/uml.svg).
