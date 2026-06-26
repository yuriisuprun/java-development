package com.suprun.repository;

import com.suprun.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Fake implementation of UserRepository that uses in-memory storage.
 *
 * This is a working implementation that simulates database behavior using a HashMap.
 * It's suitable for unit testing when you need a real UserRepository implementation
 * but don't want to depend on an actual database.
 *
 * <h2>Advantages:</h2>
 * <ul>
 *   <li>No database overhead - pure in-memory operations</li>
 *   <li>Deterministic behavior - easier to debug</li>
 *   <li>Implements real UserRepository contract</li>
 *   <li>Thread-safe for single-threaded test scenarios</li>
 * </ul>
 *
 * <h2>Limitations:</h2>
 * <ul>
 *   <li>Only implements essential methods for testing</li>
 *   <li>Not suitable for performance testing</li>
 *   <li>Data lost when instance is garbage collected</li>
 * </ul>
 *
 * @author Yurii_Suprun
 * @see UserRepository
 * @see <a href="https://xunitpatterns.com/Fake%20Object.html">Fake Object Pattern</a>
 */
public class FakeUserRepository implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    /**
     * Saves a user to the in-memory store.
     *
     * @param entity the user to save
     * @param <S> the type of user entity
     * @return the saved user
     */
    @Override
    public <S extends User> S save(S entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        users.put(entity.getId(), entity);
        return entity;
    }

    /**
     * Finds a user by ID in the in-memory store.
     *
     * @param id the user ID
     * @return Optional containing the user if found
     */
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    /**
     * Checks if a user with the given ID exists.
     *
     * @param id the user ID
     * @return true if user exists, false otherwise
     */
    @Override
    public boolean existsById(Long id) {
        return users.containsKey(id);
    }

    /**
     * Deletes a user by ID from the in-memory store.
     *
     * @param id the user ID
     */
    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

    /**
     * Deletes a user from the in-memory store.
     *
     * @param entity the user to delete
     */
    @Override
    public void delete(User entity) {
        users.remove(entity.getId());
    }

    /**
     * Returns all users currently in the store.
     *
     * @return list of all users
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    /**
     * Returns the number of users in the store.
     *
     * @return count of users
     */
    @Override
    public long count() {
        return users.size();
    }

    /**
     * Clears all users from the in-memory store.
     */
    @Override
    public void deleteAll() {
        users.clear();
    }

    // ===== Unsupported Operations =====
    // The following methods are not implemented as they are not essential
    // for the basic test scenarios. Override as needed.

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException("deleteAllById not implemented in FakeUserRepository");
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        throw new UnsupportedOperationException("deleteAll(Iterable) not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("saveAll not implemented in FakeUserRepository");
    }

    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException("findAllById not implemented in FakeUserRepository");
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("flush not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("saveAndFlush not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException("saveAllAndFlush not implemented in FakeUserRepository");
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {
        throw new UnsupportedOperationException("deleteAllInBatch not implemented in FakeUserRepository");
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        throw new UnsupportedOperationException("deleteAllByIdInBatch not implemented in FakeUserRepository");
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("deleteAllInBatch not implemented in FakeUserRepository");
    }

    @Override
    public User getOne(Long aLong) {
        throw new UnsupportedOperationException("getOne not implemented in FakeUserRepository");
    }

    @Override
    public User getById(Long aLong) {
        throw new UnsupportedOperationException("getById not implemented in FakeUserRepository");
    }

    @Override
    public User getReferenceById(Long aLong) {
        throw new UnsupportedOperationException("getReferenceById not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("findOne not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("findAll(Example) not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("findAll(Example, Sort) not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("findAll(Example, Pageable) not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        throw new UnsupportedOperationException("count(Example) not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("exists(Example) not implemented in FakeUserRepository");
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("findBy not implemented in FakeUserRepository");
    }

    @Override
    public List<User> findAll(Sort sort) {
        throw new UnsupportedOperationException("findAll(Sort) not implemented in FakeUserRepository");
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("findAll(Pageable) not implemented in FakeUserRepository");
    }
}
