# Design Patterns Module

This module provides implementations of commonly used design patterns in Java, organized into three main categories: Behavioral, Creational, and Structural patterns.

## Project Structure

```
design-patterns/
├── src/main/java/com/suprun/designpatterns/
│   ├── behavioral/
│   │   ├── observer/        - Observer Pattern
│   │   └── strategy/        - Strategy Pattern
│   ├── creational/
│   │   ├── builder/         - Builder Pattern
│   │   ├── factorymethod/   - Factory Method Pattern
│   │   └── singleton/       - Singleton Pattern
│   └── structural/
│       ├── decorator/       - Decorator Pattern
│       └── proxy/           - Proxy Pattern
```

## Behavioral Patterns

### Observer Pattern
**Location:** `behavioral/observer/`

The Observer pattern defines a one-to-many relationship between objects, where when one object (subject) changes state, all dependent objects (observers) are notified automatically.

**Key Components:**
- `Observer` - Interface for objects that receive notifications
- `Subject` - Interface for objects that notify observers
- `WeatherStation` - Concrete subject that notifies observers of temperature changes
- `PhoneDisplay` - Concrete observer that displays temperature
- `WindowDisplay` - Concrete observer that displays temperature

**Demo:** `ObserverDemo.java`

### Strategy Pattern
**Location:** `behavioral/strategy/`

The Strategy pattern lets you define a family of algorithms, encapsulate each one, and make them interchangeable.

**Key Components:**
- `PaymentStrategy` - Interface for different payment strategies
- `CreditCardPayment` - Concrete strategy for credit card payments
- `PayPalPayment` - Concrete strategy for PayPal payments
- `ShoppingCart` - Context that uses the payment strategy

**Demo:** `Main.java`

## Creational Patterns

### Singleton Pattern
**Location:** `creational/singleton/`

The Singleton pattern ensures that a class has only one instance and provides a global point of access to it.

**Key Components:**
- `Singleton` - Classic singleton with double-checked locking (thread-safe, lazy-initialized)
- `EnumSingleton` - Enum-based singleton (recommended approach, protects against reflection)

**Demo:** `SingletonMain.java`

### Factory Method Pattern
**Location:** `creational/factorymethod/`

The Factory Method pattern provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created.

**Key Components:**
- `Animal` - Interface for animal objects
- `Dog` - Concrete animal implementation
- `Cat` - Concrete animal implementation
- `AnimalFactory` - Factory for creating animals

**Demo:** `Main.java`

### Builder Pattern
**Location:** `creational/builder/`

The Builder pattern separates the construction of a complex object from its representation, allowing the same construction process to create different representations.

**Key Components:**
- `Car` - Immutable object representing a car
- `Car.CarBuilder` - Builder for constructing Car instances

**Demo:** `BuilderMain.java`

## Structural Patterns

### Decorator Pattern
**Location:** `structural/decorator/`

The Decorator pattern attaches additional responsibilities to an object dynamically, providing a flexible alternative to subclassing.

**Key Components:**
- `Coffee` - Interface for coffee objects
- `BasicCoffee` - Concrete component (base coffee)
- `CoffeeDecorator` - Abstract decorator
- `MilkDecorator` - Concrete decorator (adds milk)
- `SugarDecorator` - Concrete decorator (adds sugar)

**Demo:** `Main.java`

### Proxy Pattern
**Location:** `structural/proxy/`

The Proxy pattern provides a surrogate or placeholder for another object to control access to it.

**Key Components:**
- `BankAccount` - Interface for bank account operations
- `BankAccountImpl` - Real subject (actual bank account)
- `SecureBankAccountProxy` - Proxy that adds security checks

**Demo:** `ProxyMain.java`

## Running the Demos

Each pattern has a demo class that shows how to use it:

```bash
# Observer Pattern
javac behavioral/observer/*.java
java com.suprun.designpatterns.behavioral.observer.ObserverDemo

# Strategy Pattern
javac behavioral/strategy/*.java
java com.suprun.designpatterns.behavioral.strategy.Main

# Singleton Pattern
javac creational/singleton/*.java
java com.suprun.designpatterns.creational.singleton.SingletonMain

# Factory Method Pattern
javac creational/factorymethod/*.java
java com.suprun.designpatterns.creational.factorymethod.Main

# Builder Pattern
javac creational/builder/*.java
java com.suprun.designpatterns.creational.builder.BuilderMain

# Decorator Pattern
javac structural/decorator/*.java
java com.suprun.designpatterns.structural.decorator.Main

# Proxy Pattern
javac structural/proxy/*.java
java com.suprun.designpatterns.structural.proxy.ProxyMain
```

## Key Improvements in This Refactoring

1. **Access Modifiers** - Made all interfaces public for proper encapsulation
2. **Documentation** - Added comprehensive Javadoc comments to all classes and methods
3. **Input Validation** - Added null checks and validation using `Objects.requireNonNull()`
4. **Constants** - Extracted magic numbers into named constants
5. **Code Organization** - Separated WindowDisplay into its own file
6. **Immutability** - Made appropriate fields `final` for thread-safety
7. **Security** - Added card number and email masking for sensitive data
8. **Demo Clarity** - Improved demo classes with better output and error handling
9. **Separation of Concerns** - Moved main methods from model classes to separate demo classes

## Design Pattern Benefits

- **Observer** - Decouples subjects from observers, enables dynamic subscription
- **Strategy** - Enables runtime algorithm selection, promotes code reuse
- **Singleton** - Ensures single instance, controls resource allocation
- **Factory Method** - Encapsulates object creation, promotes loose coupling
- **Builder** - Simplifies construction of complex objects, improves readability
- **Decorator** - Adds behavior dynamically without modifying existing classes
- **Proxy** - Controls access to objects, adds cross-cutting concerns

## Author

Yurii Suprun

