package com.comp3095.assn1.model;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Recipe() {};

    public Recipe(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public User getUser() { return user; }

    public void setName(String name) {

        this.name = name;
    }
    public void setUser(User user) {
        this.user = user;
    }
}