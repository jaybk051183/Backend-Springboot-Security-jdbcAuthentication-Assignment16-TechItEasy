package com.example.les13relationstechiteasy.repository;

import com.example.les13relationstechiteasy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
