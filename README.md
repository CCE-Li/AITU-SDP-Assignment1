# Builder Pattern – Pizza Example

**Course:** ShP-2216 – Software Design Patterns  
**Assignment:** #1 – Builder Pattern  
**Language:** Java 17  

## What is this project?

This project demonstrates the **Builder creational design pattern** by constructing different kinds of pizzas step by step.

Two concrete builders produce meaningfully different representations:

| Builder                  | Style              | Typical Characteristics                     |
|--------------------------|--------------------|---------------------------------------------|
| `MargheritaPizzaBuilder` | Classic Italian    | Thin crust, tomato sauce, mozzarella, basil |
| `PepperoniPizzaBuilder`  | American spicy     | Thick crust, spicy sauce, lots of pepperoni |

A `PizzaDirector` is provided for reusable standard configurations, while the fluent API still allows full customization.

## Project Structure

```
builder-pattern-pizza/
├── README.md
├── src/main/java/com/astana/pizza/
│   ├── Pizza.java                  # Product (immutable)
│   ├── PizzaBuilder.java           # Builder interface
│   ├── MargheritaPizzaBuilder.java # ConcreteBuilder 1
│   ├── PepperoniPizzaBuilder.java  # ConcreteBuilder 2
│   ├── PizzaDirector.java          # Director (optional but used)
│   └── Client.java                 # Demo / main class
```

## How to run

### Option 1 – Compile & run from command line

```bash
# from the project root
javac -d out src/main/java/com/astana/pizza/*.java
java -cp out com.astana.pizza.Client
```

### Option 2 – IntelliJ IDEA

1. Open the project folder as a new project.
2. Mark `src/main/java` as Sources Root (right-click → Mark Directory as → Sources Root).
3. Run the `Client` class.

## How the builders work

### Using the Director (recommended for standard pizzas)

```java
PizzaBuilder builder = new MargheritaPizzaBuilder();
PizzaDirector director = new PizzaDirector(builder);

Pizza pizza = director.makeClassic();
```

### Fluent API (direct usage)

```java
Pizza pizza = new PepperoniPizzaBuilder()
        .setName("Extra Hot")
        .setDough("Stuffed Crust")
        .setSauce("Arrabbiata")
        .addTopping("Jalapeños")
        .setSpicy(true)
        .build();
```

## Design decisions

- **Immutable Product** – once a `Pizza` is built it cannot be changed.
- **Method chaining** – every setter returns the builder (`this`).
- **Validation** – `build()` throws `IllegalStateException` when required fields are missing.
- **No duplicated construction logic** – common validation lives in `Pizza.BuilderState`.
- **Director is optional** – you can still use the builders directly.

## Clean Code principles applied

1. Meaningful, intention-revealing names
2. Small methods that do one thing
3. No duplicated construction logic between builders
4. Validated construction (`build()` throws clear exceptions)
5. No magic numbers / strings (defaults are explicit and named)
6. Immutable final product + package-private mutable state
7. Consistent formatting and focused classes

## Author

Student implementation for Astana IT University – Software Design Patterns course.
