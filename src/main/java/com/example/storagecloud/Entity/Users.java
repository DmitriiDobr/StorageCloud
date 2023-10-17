package com.example.storagecloud.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Table
@Entity(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StorageFiles> userFiles;

    public List<StorageFiles> getUserFiles() {
        return userFiles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roles) {
        this.role = roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}

