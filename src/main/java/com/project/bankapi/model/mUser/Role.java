package com.project.bankapi.model.mUser;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    @ManyToOne
    private User user;

    public Role() {  }

    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
