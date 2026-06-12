# Design Patterns Quick Reference Guide

## Quick Overview Table

| Pattern | Type | Purpose | Difficulty | Use When |
|---------|------|---------|-----------|----------|
| Observer | Behavioral | One-to-many communication | Medium | Objects need to be notified of state changes |
| Strategy | Behavioral | Interchangeable algorithms | Medium | Multiple ways to perform a task |
| Singleton | Creational | Single instance | Easy | Only one instance needed globally |
| Factory Method | Creational | Object creation | Medium | Hide creation complexity |
| Builder | Creational | Complex object construction | Medium | Complex objects with many parameters |
| Decorator | Structural | Add behavior dynamically | Hard | Need flexible feature combinations |
| Proxy | Structural | Control access | Medium | Need access control or lazy loading |

## Pattern Usage Comparison

### When to Use Each Pattern

#### Need to Notify Multiple Objects?
→ **Observer Pattern** (`behavioral/observer/`)

Observer is best when you have a one-to-many relationship where multiple objects need to react to changes in another object.

```
Real-world examples:
- Event listeners in UI frameworks
- MVC pattern (model notifications to views)
- Pub/Sub messaging systems
- Real-time data updates
```

#### Need to Switch Between Algorithms?
→ **Strategy Pattern** (`behavioral/strategy/`)

Strategy is best when you have multiple ways to accomplish a task and need to choose between them at runtime.

```
Real-world examples:
- Different payment methods (credit card, PayPal, cash)
- Sorting algorithms (quick sort, merge sort, bubble sort)
- Compression strategies (ZIP, RAR, 7Z)
- Search algorithms (breadth-first, depth-first)
```

#### Need Only One Instance of a Class?
→ **Singleton Pattern** (`creational/singleton/`)

Singleton ensures a class has only one instance and provides global access to it.

```
Real-world examples:
- Database connection pool
- Logger instances
- Configuration managers
- Cache managers
- Thread pools
```

#### Need to Hide Object Creation Complexity?
→ **Factory Method Pattern** (`creational/factorymethod/`)

Factory Method is best when you need to create objects without specifying their exact classes.

```
Real-world examples:
- Database connection factories
- UI component factories
- Service locators
- Object serialization factories
```

#### Building Complex Objects with Many Parameters?
→ **Builder Pattern** (`creational/builder/`)

Builder is best for constructing complex objects that have multiple optional parameters.

```
Real-world examples:
- SQL query builders
- String builders (StringBuilder)
- GUI widget configuration
- Configuration objects
- HTTP request builders
```

#### Need to Add Features Without Modifying Classes?
→ **Decorator Pattern** (`structural/decorator/`)

Decorator is best when you need to add behavior to objects dynamically without changing their structure.

```
Real-world examples:
- GUI components with scrollbars and borders
- Stream filtering (BufferedInputStream, etc.)
- UI component styling
- Coffee shop drink customization
- Feature additions to objects
```

#### Need to Control Access to Another Object?
→ **Proxy Pattern** (`structural/proxy/`)

Proxy is best when you need to control access to an object, add security checks, or implement lazy loading.

```
Real-world examples:
- Remote object proxies (RMI)
- Virtual proxy (lazy loading)
- Protection proxy (security checks)
- Logging proxy (method call logging)
- Caching proxy (result caching)
```

## Anti-Patterns to Avoid

### ❌ Don't Use Singleton When:
- Multiple instances are needed
- Testing requires multiple instances
- Thread-safety is not properly considered

### ❌ Don't Use Factory Method When:
- Object creation is simple and straightforward
- You want explicit type information

### ❌ Don't Use Decorator When:
- The feature set is fixed (use inheritance)
- You need deep hierarchies (causes complexity)

### ❌ Don't Use Proxy When:
- Performance overhead is critical
- Simple inheritance would suffice

## Pattern Complexity vs. Benefit

```
Complexity (Low to High)
↓
Singleton ──► Factory Method ──► Strategy ──► Observer ──► Builder ──► Proxy ──► Decorator
↓
Easy to implement, great for simple singleton needs
                 Good for factory logic
                          Good for runtime algorithm switching
                                   Good for event systems
                                            Good for complex construction
                                                    Good for access control
                                                             More complex, great flexibility
```

## Common Mistakes

### Singleton Pattern
- ❌ Not considering multi-threading
- ✅ Use `synchronized` or eager initialization
- ✅ Consider enum-based singleton for reflection protection

### Factory Pattern
- ❌ Making factory methods too simple
- ✅ Use when there's complex initialization logic
- ✅ Return interfaces, not concrete classes

### Builder Pattern
- ❌ Over-engineering simple objects
- ✅ Use when objects have many optional parameters
- ❌ Don't use for simple POJOs with 2-3 fields

### Decorator Pattern
- ❌ Creating too many decorator classes
- ✅ Limit decorator combinations
- ❌ Don't use when inheritance would work better

### Observer Pattern
- ❌ Forgetting to unregister observers (memory leaks)
- ✅ Always implement removeObserver functionality
- ✅ Be careful with strong references

### Strategy Pattern
- ❌ Over-abstracting simple conditionals
- ✅ Use when you have multiple substantial algorithms
- ✅ Consider type safety with generic strategies

### Proxy Pattern
- ❌ Making proxies too complex
- ✅ Keep proxy logic focused
- ❌ Don't use for simple delegation

## Implementation Checklist

### Before Implementing a Pattern

- [ ] Understand the problem you're solving
- [ ] Identify which pattern best fits your use case
- [ ] Check if simpler solution exists (often does!)
- [ ] Consider performance implications
- [ ] Plan for thread-safety if needed
- [ ] Think about testing strategy
- [ ] Document why you chose this pattern
- [ ] Plan for future maintenance

### After Implementation

- [ ] Add comprehensive documentation
- [ ] Write unit tests
- [ ] Test thread-safety if applicable
- [ ] Code review for pattern correctness
- [ ] Verify memory implications
- [ ] Test with realistic data volumes
- [ ] Update architecture documentation

## Performance Considerations

| Pattern | Memory Overhead | CPU Overhead | When Problematic |
|---------|-----------------|--------------|------------------|
| Singleton | Low | Low | Large objects, many instances needed |
| Factory | Low | Low | Extreme high throughput scenarios |
| Strategy | Medium | Medium | Real-time systems, tight loops |
| Observer | Medium | Medium | Many observers, called frequently |
| Builder | Medium | Medium | Millions of temporary objects |
| Decorator | Low-Medium | Low | Deep decorator chains |
| Proxy | Medium | Medium | Frequently accessed objects |

## Testing Patterns

### Singleton Testing
```java
// Test that getInstance returns same instance
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();
assertEqual(s1, s2);
```

### Factory Testing
```java
// Test factory creates correct type
Animal dog = AnimalFactory.createAnimal("dog");
assertInstanceOf(Dog.class, dog);
```

### Strategy Testing
```java
// Test strategy execution
ShoppingCart cart = new ShoppingCart();
cart.setPaymentStrategy(new MockPaymentStrategy());
cart.checkout(100);
verify(mockStrategy).pay(100);
```

### Observer Testing
```java
// Test observer is notified
Observer mockObserver = mock(Observer.class);
subject.registerObserver(mockObserver);
subject.notifyObservers();
verify(mockObserver).update(anyFloat());
```

### Builder Testing
```java
// Test builder configuration
Car car = new Car.CarBuilder("V8", 4)
    .setAirConditioning(true)
    .build();
assertTrue(car.hasAirConditioning());
```

### Decorator Testing
```java
// Test decorated object behavior
Coffee coffee = new BasicCoffee();
coffee = new MilkDecorator(coffee);
assertEquals(2.5, coffee.getCost());
assertTrue(coffee.getDescription().contains("milk"));
```

### Proxy Testing
```java
// Test proxy controls access
BankAccount account = new SecureBankAccountProxy(1000);
account.deposit(-100); // Should be rejected
assertEquals(1000, account.getBalance());
```

## References & Further Reading

- Gang of Four (GoF) Design Patterns book
- Head First Design Patterns
- Refactoring.guru - Design Patterns
- Wikipedia - Software Design Patterns
- Oracle Java Documentation - Design Patterns

## Module Navigation

```
src/main/java/com/suprun/designpatterns/
│
├─ behavioral/
│  ├─ observer/        ← Start here for event-driven systems
│  └─ strategy/        ← Start here for algorithm switching
│
├─ creational/
│  ├─ singleton/       ← Start here for single instances
│  ├─ factorymethod/   ← Start here for object creation
│  └─ builder/         ← Start here for complex objects
│
└─ structural/
   ├─ decorator/       ← Start here for feature composition
   └─ proxy/           ← Start here for access control
```

---
**Last Updated:** June 2026
**Author:** Yurii Suprun

