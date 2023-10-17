package com.example.storagecloud.Repository;

import com.example.storagecloud.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Нужно для авторизации через токен
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByPassword(String password);


}
