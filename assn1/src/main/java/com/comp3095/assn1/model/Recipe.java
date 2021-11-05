package com.comp3095.assn1.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "Ingredients")
    private String Ingredients;



    @Column(name = "Directions")
    private String Directions;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Recipe() {};

    public Recipe(String name, User user, String Ingredients, String Directions) {
        this.name = name;
        this.user = user;
        this.Ingredients = Ingredients;
        this.Directions = Directions;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public User getUser() { return user; }
    public String getIngredients(){return Ingredients;}
    public String getDirections(){return Directions;}

    public void setName(String name) {

        this.name = name;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public void setDirections(String directions) {
        Directions = directions;
    }
}