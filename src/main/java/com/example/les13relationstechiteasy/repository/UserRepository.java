package com.example.les13relationstechiteasy.repository;

import com.example.les13relationstechiteasy.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<model, id> {
    List<User> findAll();

    Optional<User> findById(String username);

    boolean existsById(String username);

    User save(User user);
}
