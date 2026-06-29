# Java 8 Method References

This module demonstrates the concept of **Method References** in Java 8, a powerful feature that provides a concise way to refer to methods without invoking them.

## Overview

Method references are a shorthand syntax for lambda expressions that call a single method. They are more readable and cleaner than lambda expressions when you're simply calling an existing method.

### Key Benefits
- **Readability**: More readable than lambda expressions when calling existing methods
- **Reusability**: Promotes code reuse and maintainability
- **Cleanliness**: Eliminates unnecessary lambda wrapper code
- **Performance**: Generally performs similarly to lambda expressions due to compiler optimizations

## Types of Method References

### 1. **Static Method References** (`ClassName::staticMethodName`)
References to static methods can be used directly from the class name.

```java
// Lambda expression
words.stream().map(word -> word.toUpperCase()).collect(Collectors.toList());

// Method reference
words.stream().map(String::toUpperCase).collect(Collectors.toList());
```

### 2. **Instance Method References on a Particular Object** (`object::instanceMethodName`)
References to instance methods can be used on a specific object instance.

```java
MethodReferences methodReferences = new MethodReferences();

// Lambda expression
names.forEach(name -> methodReferences.printName(name));

// Method reference
names.forEach(methodReferences::printName);
```

### 3. **Instance Method References on an Arbitrary Object** (`ClassName::instanceMethodName`)
References to instance methods that will be called on different objects of the same type.

```java
// Lambda expression
words.stream().map(word -> word.length()).collect(Collectors.toList());

// Method reference
words.stream().map(String::length).collect(Collectors.toList());
```

### 4. **Constructor References** (`ClassName::new`)
References to constructors for creating new instances.

```java
// Lambda expression
names.stream().map(name -> new Person(name)).collect(Collectors.toList());

// Method reference
names.stream().map(Person::new).collect(Collectors.toList());
```

## Common Use Cases

### Filtering with Method References
```java
List<String> nonEmpty = words.stream()
                              .filter(Predicate.not(String::isEmpty))
                              .collect(Collectors.toList());
```

### Transforming with Method References
```java
List<Integer> lengths = words.stream()
                              .map(String::length)
                              .collect(Collectors.toList());
```

### Sorting with Method References
```java
List<String> sorted = words.stream()
                            .sorted(String::compareTo)
                            .collect(Collectors.toList());
```

### Using System Output
```java
Consumer<String> printer = System.out::println;
names.forEach(printer);
```

### Parsing with Method References
```java
List<Integer> numbers = strings.stream()
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());
```

## Method Reference Syntax

| Type | Syntax | Example |
|------|--------|---------|
| Static Method | `ClassName::staticMethod` | `Integer::parseInt` |
| Instance Method (Particular Object) | `object::method` | `myObject::toString` |
| Instance Method (Arbitrary Object) | `ClassName::method` | `String::toUpperCase` |
| Constructor | `ClassName::new` | `ArrayList::new` |

## Functional Interfaces

Method references work seamlessly with functional interfaces. Common functional interfaces include:

- `Supplier<T>`: No arguments, returns T
- `Consumer<T>`: Takes T, returns nothing
- `Function<T, R>`: Takes T, returns R
- `Predicate<T>`: Takes T, returns boolean
- `BinaryOperator<T>`: Takes two T values, returns T

## Running Tests

To run the tests for this module:

```bash
mvn clean test
```

To compile and test:

```bash
mvn clean compile test
```

## Files

- **MethodReferences.java**: Main class demonstrating various method reference types and use cases
- **MethodReferencesTest.java**: Comprehensive test suite with 20+ test cases covering all method reference types

## Learning Path

1. Start with static method references (most straightforward)
2. Move to instance method references on particular objects
3. Explore arbitrary object method references (slightly more complex)
4. Learn about constructor references
5. Combine method references with functional interfaces and streams

## Related Topics

- [Lambda Expressions](../lambda-expression/README.md)
- [Functional Interfaces](../functional-interface/README.md)
- [Stream API](../stream-api/README.md)

## Best Practices

1. **Use method references when they improve readability**: If the lambda is just calling a single method, use a method reference.
2. **Avoid over-using method references**: If the logic is complex, a lambda might be more readable.
3. **Combine with streams**: Method references shine when used with the Stream API.
4. **Consider custom functional interfaces**: Define your own functional interfaces for specific use cases.
5. **Profile if performance is critical**: Though generally similar, always profile in performance-critical code.

## Common Pitfalls

- **Type mismatches**: Ensure the method reference's signature matches the functional interface
- **Null pointers**: Be careful when using instance method references - the object must not be null
- **Overloading**: When multiple overloads exist, the compiler determines which one to use based on context

---

For more information on Java 8 features, see the main project README.
