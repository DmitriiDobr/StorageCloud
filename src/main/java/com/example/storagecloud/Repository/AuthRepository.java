package com.example.storagecloud.Repository;

import com.example.storagecloud.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String login);

}
