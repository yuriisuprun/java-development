# Test Doubles - Comprehensive Guide

This module demonstrates all five fundamental test double patterns used in Java and software testing. Test doubles are objects that replace real objects in tests to simplify testing and control dependencies.

## Overview

Test doubles are essential tools for unit testing. This guide explains when, how, and why to use each pattern.

### Quick Reference Table

| Pattern | Type | Location | Real Logic | Verifies | Use When |
|---------|------|----------|-----------|----------|----------|
| **Dummy** | Simple | Test code | None | No | Need to satisfy method signatures |
| **Fake** | Working | Main code | Simplified | No | Need functional alternative to real implementation |
| **Stub** | Mock-based | Test code | Predetermined | No | Need specific return values |
| **Mock** | Mock-based | Test code | Predetermined | Yes | Need to verify interactions |
| **Spy** | Mock-based | Test code | Real + selective | Yes | Need real behavior + selective mocking |

---

## Pattern Details

### 1. Dummy

A Dummy is an object that is passed around but never actually used in the test. It exists solely to satisfy method signatures and parameter requirements.

**Characteristics:**
- Simplest test double pattern
- No framework needed
- No verification of behavior
- Object is essentially ignored in the test

**Example Use Case:**
```java
@Test
void testProcessingSucceeds() {
    UserService service = new UserService();
    User dummyUser = new User(1L, "dummy", "dummy@test.com"); // Never used
    
    service.processUser(dummyUser); // Required parameter, but irrelevant to test
    
    assertTrue(service.isProcessed());
}
```

**When to Use:**
- Method signature requires a parameter you don't care about
- Testing behavior that doesn't depend on the object state
- Need minimum setup for a test
- Code path is independent of input

**When NOT to Use:**
- The object actually affects the test outcome
- You need to verify behavior related to the object
- It makes test intent unclear

**See:** `DummyTest.java`

---

### 2. Fake

A Fake is a working implementation that provides simplified behavior. Unlike stubs or mocks, a Fake implements real logic, but in a lightweight or simplified way.

**Characteristics:**
- Implements the real interface
- Located in main source code (not test code)
- Provides working implementation
- Usually uses simplified storage (e.g., HashMap instead of database)
- Can be reused across tests

**Implementation:**
```java
public class FakeUserRepository implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    
    @Override
    public <S extends User> S save(S entity) {
        users.put(entity.getId(), entity);
        return entity;
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }
}
```

**Example Usage:**
```java
@Test
void testUserPersistence() {
    UserRepository fakeRepo = new FakeUserRepository();
    UserService service = new UserService(fakeRepo);
    
    User user = new User(1L, "John", "john@example.com");
    service.saveUser(user);
    
    Optional<User> retrieved = fakeRepo.findById(1L);
    assertTrue(retrieved.isPresent());
    assertEquals("John", retrieved.get().getName());
}
```

**Advantages:**
- Tests real behavior and interfaces
- Faster than external dependencies
- Deterministic and easier to debug
- Can catch issues with interface contracts
- Reusable across multiple tests

**Disadvantages:**
- Requires maintaining the Fake implementation
- May not catch issues in the real implementation
- Extra code to maintain

**When to Use:**
- Need alternative to expensive operations (database, file I/O)
- Testing a component that depends on an interface
- Want deterministic test behavior
- Need a real implementation but faster than production

**When NOT to Use:**
- Simple return value is sufficient (use Stub instead)
- Complex behavior that mirrors real implementation too closely
- Fake would require too much maintenance effort

**See:** `FakeTest.java` and `FakeUserRepository.java`

---

### 3. Stub

A Stub is a test double that provides predetermined answers to method calls. Stubs do NOT verify that methods were called.

**Characteristics:**
- Returns configured values
- Does NOT verify method calls
- Simple focused setup using Mockito
- Often using `when().thenReturn()` pattern

**Example Usage:**
```java
@Mock
private UserRepository userRepository;

@Test
void testRetrievesUserData() {
    User user = new User(1L, "John", "john@example.com");
    
    // Configure stub to return this user
    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    
    // Test your business logic using the stubbed return value
    Optional<User> retrieved = userService.getUserById(1L);
    assertTrue(retrieved.isPresent());
}
```

**Advantages:**
- Simple to set up
- Clear focus on return values
- Tests business logic without implementation details
- Can test multiple code paths easily

**Disadvantages:**
- Doesn't verify method calls (can mask bugs)
- No guarantee methods are actually called
- Can lead to incomplete testing

**When to Use:**
- Only care about return values, not method invocation
- Testing different code paths with different data
- Simple dependency scenarios
- Want to avoid over-specification

**When NOT to Use:**
- Need to verify the method was actually called
- Interaction pattern is important to your design
- Method call has side effects you need to verify

**See:** `StubTest.java`

---

### 4. Mock

A Mock is a test double that both provides predetermined behavior AND verifies that expected methods are called with expected parameters. Mocks enforce interaction contracts.

**Characteristics:**
- Combines stub behavior with verification
- Verifies method was called
- Can verify call count and arguments
- Using `when().thenReturn()` and `verify()`

**Example Usage:**
```java
@Mock
private UserRepository userRepository;

@Test
void testSavesUserToRepository() {
    User user = new User(1L, "John", "john@example.com");
    Mockito.when(userRepository.save(user)).thenReturn(user);
    
    userService.saveUser(user);
    
    // Verify that save was called
    verify(userRepository).save(user);
}
```

**Advanced Verification:**
```java
@Test
void testVerifiesInteractionPatterns() {
    verify(userRepository, times(2)).save(any());      // Called twice
    verify(userRepository, never()).delete(any());    // Never called
    verify(userRepository).findById(1L);              // Called with specific arg
}
```

**Advantages:**
- Verifies interactions and contracts
- Catches when expected behavior doesn't occur
- Helps detect over/under-calling
- Excellent for testing edge cases

**Disadvantages:**
- Can create brittle tests
- Over-specification of interactions
- Can test implementation instead of behavior
- Requires careful use to avoid false positives

**When to Use:**
- Interaction pattern is part of the contract
- Need to verify a method was called
- Testing error handling or side effects
- Method call count matters
- Verifying specific arguments passed

**When NOT to Use:**
- Don't care if method is called (use Stub)
- Over-specifying implementation details
- Simple return value is all that matters
- Leads to fragile tests

**See:** `MockTest.java`

---

### 5. Spy

A Spy is a partial mock that wraps a real object. Spies call real methods by default but allow selective stubbing and verification of specific methods.

**Characteristics:**
- Wraps the real object
- Calls real methods unless explicitly stubbed
- Allows selective mocking of specific methods
- Uses `doReturn().when()` for stubbing void methods
- Can verify calls on real objects

**Example Usage:**
```java
@Spy
private UserService userService;

@Test
void testWithSelectiveMocking() {
    User user = new User(1L, "John", "john@example.com");
    
    // Stub only specific method
    Mockito.doReturn(true).when(userService).isProcessed();
    
    // Other methods run with real implementation
    userService.processUser(user);
    
    // Verify the real method was called
    verify(userService).processUser(user);
}
```

**Important Note - Use doReturn() for Spies:**
```java
// ✓ CORRECT: Use doReturn for spies
Mockito.doReturn(true).when(userService).isProcessed();

// ✗ WRONG: Avoid with spies (can trigger real method)
Mockito.when(userService.isProcessed()).thenReturn(true);
```

**Advantages:**
- Tests mostly real behavior
- Flexible selective mocking
- Can spy on legacy code
- Allows verification of real object interactions

**Disadvantages:**
- More complex to understand
- Can create brittle tests
- Couples tests to implementation
- Easy to misuse (when vs doReturn)
- Can hide design issues

**When to Use:**
- Want real behavior but mock specific dependencies
- Testing legacy code with hard-to-inject dependencies
- Need verification of actual method calls
- Testing mostly real implementation with one mock
- Gradually refactoring code

**When NOT to Use:**
- Can refactor to use pure Mocks
- Need all behavior mocked
- Implementation is simple enough for pure Mocks
- Overusing spies indicates design issues

**See:** `SpyTest.java`

---

## Test Data Builder

The `UserBuilder` class provides a fluent API for creating test data consistently:

```java
// Using builder
User user = new UserBuilder()
    .withId(1L)
    .withName("John Doe")
    .withEmail("john@example.com")
    .build();

// Factory methods for common cases
User defaultUser = UserBuilder.createDefaultUser();
User userWithId = UserBuilder.createUserWithId(5L);
User userWithName = UserBuilder.createUserWithName("Jane");
```

**Benefits:**
- Consistent test data creation
- Readable test setup
- Easy to modify test data
- Sensible defaults reduce boilerplate

---

## Running the Tests

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn clean test -Dtest=DummyTest
mvn clean test -Dtest=FakeTest
mvn clean test -Dtest=StubTest
mvn clean test -Dtest=MockTest
mvn clean test -Dtest=SpyTest

# Run with verbose output
mvn test -X
```

---

## Best Practices

### 1. Choose the Right Pattern
- Start with the simplest pattern that works
- Use Dummy for unused parameters
- Use Fake when you need working behavior
- Use Stub for simple return values
- Use Mock when interaction matters
- Use Spy carefully and as last resort

### 2. Keep Tests Focused
```java
// Good: One concept per test
@Test
void testSavesUserWithValidData() { }

// Avoid: Multiple concepts
@Test
void testSavesUserAndRetrievesAndDeletesAndVerifies() { }
```

### 3. Use Descriptive Names
```java
// Good
void testReturnsEmptyWhenUserNotFound() { }

// Avoid
void test1() { }
void testStuff() { }
```

### 4. Follow AAA Pattern (Arrange-Act-Assert)
```java
@Test
void testExample() {
    // Arrange: Set up test data and mocks
    User user = new UserBuilder().withId(1L).build();
    Mockito.when(repo.findById(1L)).thenReturn(Optional.of(user));
    
    // Act: Execute the code under test
    Optional<User> result = service.getUserById(1L);
    
    // Assert: Verify the results
    assertTrue(result.isPresent());
}
```

### 5. Don't Over-Specify Interactions
```java
// Good: Verify what matters
verify(userRepository).save(any());

// Avoid: Over-specifying
verify(userRepository).save(captor.capture());
assertEquals("John", captor.getValue().getName());
// (If you don't care about the name, don't verify it)
```

### 6. Keep Mocks in Separate Methods
```java
// Good
private void setupUserRepositoryMock(User user) {
    Mockito.when(userRepository.findById(user.getId()))
        .thenReturn(Optional.of(user));
}

@Test
void testRetrievesUser() {
    User user = UserBuilder.createDefaultUser();
    setupUserRepositoryMock(user);
    // ...
}
```

---

## Common Mistakes

### ❌ Using Mocks When Stubs Suffice
```java
// Avoid: Over-specification
@Mock UserRepository repo;

@Test
void test() {
    Mockito.when(repo.findById(1L)).thenReturn(Optional.of(user));
    service.getUserById(1L);
    verify(repo).findById(1L);  // ← Unnecessary verification
}

// Better: Use real implementation or Fake if interaction doesn't matter
```

### ❌ Misusing Spies
```java
// Avoid: Mixing when() and real calls
@Spy UserService service;

@Test
void testBad() {
    // This calls the real method during setup!
    Mockito.when(service.isProcessed()).thenReturn(true);
}

// Better: Use doReturn() for spies
@Test
void testGood() {
    Mockito.doReturn(true).when(service).isProcessed();
}
```

### ❌ Brittle Tests With Over-Verification
```java
// Avoid: Tests break for implementation changes
verify(repo, times(2)).save(any());  // ← Couples to implementation

// Better: Verify behavior, not implementation
assertTrue(service.isSaved());
```

### ❌ Mocking Dependencies You Could Inject
```java
// Avoid: Over-mocking
@Mock DatabaseConnection conn;
@Mock CacheService cache;
@Mock LogService log;

// Better: Use Fake or real implementation for what you can
UserRepository fakeRepo = new FakeUserRepository();
UserService service = new UserService(fakeRepo);  // Inject what matters
```

---

## Architecture

```
test-doubles/
├── src/main/java/com/suprun/
│   ├── model/
│   │   └── User.java                    # Entity model
│   ├── repository/
│   │   ├── UserRepository.java          # Interface
│   │   └── FakeUserRepository.java      # Fake implementation
│   └── service/
│       └── UserService.java             # Business logic
└── test/java/com/suprun/
    ├── testutil/
    │   └── UserBuilder.java             # Test data builder
    ├── DummyTest.java                   # Dummy pattern tests
    ├── FakeTest.java                    # Fake pattern tests
    ├── StubTest.java                    # Stub pattern tests
    ├── MockTest.java                    # Mock pattern tests
    └── SpyTest.java                     # Spy pattern tests
```

---

## References

- [xUnit Patterns - Gerard Meszaros](https://xunitpatterns.com/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Google Testing Blog - Test Doubles](https://testing.googleblog.com/)
- [Martin Fowler - Mocks Aren't Stubs](https://martinfowler.com/articles/mocksArentStubs.html)

---

## Related Concepts

- **Test Isolation**: Each test should be independent
- **Test Fixture**: Set up needed before test runs
- **Assertion**: Verification of expected behavior
- **Coverage**: How much code is exercised by tests
- **Integration Testing**: Testing with real components (vs unit tests with doubles)

---

## Dependencies

- Java 21
- Spring Boot 3.2.5
- JUnit 5 (via spring-boot-starter-test)
- Mockito (via spring-boot-starter-test)
- Lombok (for entity annotations)

---

## Author

Yurii Suprun

---

## License

This educational material is provided as-is for learning purposes.
