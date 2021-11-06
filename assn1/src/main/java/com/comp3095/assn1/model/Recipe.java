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

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "directions")
    private String directions;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Recipe() {};

    public Recipe(String name, String ingredients, String directions, User user) {
        this.name = name;
        this.user = user;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDirections() { return directions; }
    public String getIngredients() { return ingredients; }
    public User getUser() { return user; }

    public void setName(String name) {
        this.name = name;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void setDirections(String directions) {
        this.directions = directions;
    }
}