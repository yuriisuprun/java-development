# Java Core Fundamentals - Complete Reference

**Last Updated:** June 19, 2026  
**Status:** ✅ Refactoring Complete

## Quick Navigation

### Documentation Files
- **[REFACTORING_REPORT.md](REFACTORING_REPORT.md)** - Detailed report of all refactoring changes
- **[MODULE_STRUCTURE.md](MODULE_STRUCTURE.md)** - Complete module organization and learning path
- **[REFACTORING_SUMMARY.txt](REFACTORING_SUMMARY.txt)** - Quick reference summary
- **[INDEX.md](INDEX.md)** - This file

---

## Module Overview

The `java-core-fundamentals` module demonstrates essential Java concepts through well-organized, professionally documented code.

### What's Inside

```
📦 com.suprun
├── 📄 Main.java                          Entry point with concept examples
├── 📁 annotations/                       Custom annotation demonstration
│   ├── TestAnnotation.java               Marker annotation
│   └── MagicClass.java                   Annotation usage
├── 📁 enums/                             Type-safe enum examples
│   ├── Day.java                          Simple enum with properties
│   └── Operation.java                    Enum with methods
├── 📁 equals_hashcode/                   Object equality patterns
│   └── Alpha.java                        equals()/hashCode() implementation
├── 📁 inheritance/                       Inheritance hierarchy
│   ├── Animal.java                       Base class
│   └── Dog.java                          Child class extending Animal
├── 📁 interfaces/                        Interface features showcase
│   ├── MyInterface.java                  Interface with various feature types
│   └── MyClass.java                      Interface implementation
├── 📁 list_implementations/              Custom collection implementations
│   ├── List.java                         List interface contract
│   ├── ArrayList.java                    Dynamic array-based list
│   └── LinkedList.java                   Node-based linked list
├── 📁 polymorphism/                      Method overloading examples
│   └── OverloadedMethodsClass.java       Multiple method overloads
└── 📁 util/                              Utility classes
    └── ExerciseNotCompletedException.java Custom exception
```

---

## Key Features by Package

### 🎯 Annotations (`annotations/`)
**Concepts:** Custom annotations, metadata, retention policies
- **Target:** TYPE only
- **Retention:** RUNTIME
- **Use Case:** Demonstrating annotation creation and application

### 📊 Enums (`enums/`)
**Concepts:** Type-safe enums, properties, constant-specific methods
- **Day:** Enum with properties
- **Operation:** Enum with abstract methods
- **Key Learning:** Type-safe enumeration patterns

### ✔️ Equals & HashCode (`equals_hashcode/`)
**Concepts:** Object equality, hash-based collections
- **Alpha:** Proper implementation of equals() and hashCode()
- **Key Learning:** Contract between equals() and hashCode()

### 👨‍👩‍👧‍👦 Inheritance (`inheritance/`)
**Concepts:** Class hierarchy, method overriding, super keyword
- **Animal:** Base class with common behavior
- **Dog:** Extends Animal, demonstrates inheritance
- **Key Learning:** IS-A relationships, polymorphism

### 🔌 Interfaces (`interfaces/`)
**Concepts:** Abstract types, default methods, static methods
- **MyInterface:** Demonstrates interface features:
  - Abstract methods
  - Default methods
  - Static methods
  - Nested interfaces
  - Nested classes
- **MyClass:** Concrete implementation
- **Key Learning:** Interface design and implementation

### 📋 Collections (`list_implementations/`)
**Concepts:** Data structures, generics, performance trade-offs

#### ArrayList
- **Implementation:** Dynamic array
- **Access:** O(1)
- **Insertion:** O(n), amortized O(1) for append
- **Use Case:** Frequent random access

#### LinkedList
- **Implementation:** Linked nodes
- **Access:** O(n)
- **Insertion:** O(1) at ends, O(n) in middle
- **Use Case:** Frequent insertions/deletions

### 🔄 Polymorphism (`polymorphism/`)
**Concepts:** Method overloading, compile-time polymorphism
- **OverloadedMethodsClass:** Multiple method implementations
- **Key Learning:** Method overloading patterns

### 🔧 Utilities (`util/`)
**Concepts:** Custom exceptions, error handling
- **ExerciseNotCompletedException:** Custom runtime exception
- **Key Learning:** Exception hierarchy and patterns

---

## Building & Running

### Compile the Module
```bash
mvn clean compile -DskipTests
```

### Run Tests
```bash
mvn test
```

### Generate Javadoc
```bash
mvn javadoc:javadoc
```

### Build All Modules
```bash
mvn clean install
```

---

## Learning Path

### 🟢 Beginner (Start Here)
1. Run `Main.java` to see all concepts in action
2. Read `Day.java` - simple enum example
3. Study `Animal.java` and `Dog.java` - basic inheritance
4. Review `MyInterface.java` - interface basics

### 🟡 Intermediate
1. Study `List.java` interface
2. Analyze `ArrayList` implementation
3. Understand `equals_hashcode` patterns
4. Review `OverloadedMethodsClass` - polymorphism

### 🔴 Advanced
1. Compare `ArrayList` vs `LinkedList` - trade-offs
2. Study `LinkedList` implementation - pointers
3. Analyze generic type constraints
4. Review `Operation.java` - enum patterns

---

## Documentation Quality

All classes include:
- ✅ Class-level Javadoc
- ✅ Method-level Javadoc
- ✅ Parameter documentation
- ✅ Return value documentation
- ✅ Exception documentation
- ✅ Performance analysis (where relevant)
- ✅ Usage examples

---

## Recent Improvements

### Version 2.0 - Refactoring Complete (June 19, 2026)

✅ **Fixed Issues:**
- Corrected package naming: polimorphism → polymorphism
- Fixed class visibility: Animal, Dog now public
- Enhanced null-safety in collections
- Removed mixed concerns (removed Main from Alpha)

✅ **Added Documentation:**
- Comprehensive Javadoc for all classes
- Added performance complexity analysis
- Included feature descriptions
- Added usage examples

✅ **Code Quality:**
- Added final modifiers to immutable fields
- Improved exception handling
- Better null-safety
- Consistent naming conventions

---

## Refactoring Details

### Changes Summary

| Category | Changes | Impact |
|----------|---------|--------|
| Visibility | Animal, Dog → public | Better API design |
| Naming | polimorphism → polymorphism | Standards compliance |
| Enums | Added final modifier | Immutability enforcement |
| Collections | Improved contains() | Null-safe operations |
| Documentation | Added comprehensive Javadoc | Better IDE support |
| Exceptions | Added constructors | More flexible error handling |

For detailed information, see:
- [REFACTORING_REPORT.md](REFACTORING_REPORT.md) - Complete change log
- [MODULE_STRUCTURE.md](MODULE_STRUCTURE.md) - Architecture guide

---

## Code Examples

### ArrayList Usage
```java
List<String> list = new ArrayList<>();
list.add("Hello");
list.add("World");
String first = list.get(0);  // O(1) access
```

### LinkedList Usage
```java
List<Integer> list = new LinkedList<>();
list.add(1);
list.add(0, 0);  // O(1) at beginning
```

### Interface Usage
```java
MyInterface obj = new MyClass();
obj.abstractMethod();    // Implemented method
obj.defaultMethod();     // Default implementation
MyInterface.staticMethod1();  // Static method
```

### Enum Usage
```java
Day today = Day.MONDAY;
String displayName = today.getDisplayName();

Operation op = Operation.PLUS;
double result = op.apply(5, 3);  // 8.0
```

---

## Common Questions

**Q: Should I use ArrayList or LinkedList?**  
A: Use ArrayList for frequent random access. Use LinkedList for frequent insertions at ends.

**Q: Why is contains() checking for null?**  
A: Collections should support null elements. The contains() method handles both null and non-null values.

**Q: How do generics work in these collections?**  
A: Type parameters like `<T>` provide type-safety while allowing any type. Type erasure removes them at runtime.

**Q: What's the difference between abstract methods and default methods?**  
A: Abstract methods must be implemented. Default methods provide a default implementation that can be overridden.

---

## Best Practices Demonstrated

1. **Encapsulation** - Proper access modifiers
2. **Immutability** - final fields for constants
3. **Null-Safety** - Proper null checks
4. **Documentation** - Comprehensive Javadoc
5. **Design Patterns** - Factory methods, inner classes
6. **Generics** - Type-safe collections
7. **Exception Handling** - Custom exceptions with proper hierarchy
8. **Performance** - Complexity analysis included

---

## Files Quick Reference

| File | Purpose | Difficulty |
|------|---------|-----------|
| Main.java | Entry point | ⭐ Beginner |
| Day.java | Enum example | ⭐ Beginner |
| Animal.java, Dog.java | Inheritance | ⭐⭐ Intermediate |
| MyInterface.java | Interface features | ⭐⭐ Intermediate |
| ArrayList.java | Dynamic array list | ⭐⭐⭐ Advanced |
| LinkedList.java | Linked list impl | ⭐⭐⭐ Advanced |
| Operation.java | Enum methods | ⭐⭐ Intermediate |
| Alpha.java | equals/hashCode | ⭐⭐ Intermediate |

---

## Building Status

✅ **Latest Build:** SUCCESS  
✅ **Compilation:** All modules compile without errors  
✅ **Tests:** Ready to run  
✅ **Documentation:** Complete with Javadoc  

---

## Support & Contributions

For issues, questions, or improvements:
1. Review the relevant documentation files
2. Check class Javadoc comments
3. Review test files for usage examples
4. Refer to REFACTORING_REPORT.md for changes

---

## License & Attribution

**Author:** Yurii_Suprun  
**Created:** As educational material for Java fundamentals  
**Updated:** June 19, 2026

---

## Additional Resources

### Within This Module
- REFACTORING_REPORT.md - What changed and why
- MODULE_STRUCTURE.md - How it's organized
- REFACTORING_SUMMARY.txt - Quick reference

### Related Documentation
- Javadoc comments in source files
- Test files in src/test/java/com/suprun/

---

**Ready to learn Java fundamentals? Start with Main.java! 🚀**
