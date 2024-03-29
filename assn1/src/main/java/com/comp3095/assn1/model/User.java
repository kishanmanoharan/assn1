// -----------------------------------
//  Project: Recipes App
//  Assignment: Assignment 1
//  Author: Kishan Manoharan, Jeff Mcilveen
//  Student numbers: 101266708, 100681357
//  Date: 11/07/2021
//  Description: User Object
//------------------------------------

package com.comp3095.assn1.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Recipe> recipes = new HashSet<Recipe>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Meal> meals = new HashSet<Meal>();

    @ManyToMany()
    private Set<Recipe> favourites = new HashSet<Recipe>();

    public User() {};

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Set<Recipe> getFavourites() { return favourites; }
    public Set<Recipe> getRecipes() { return recipes; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
    public void addRecipes(Recipe recipe) { this.recipes.add(recipe); }
    public void deleteRecipes(Recipe recipe) { this.recipes.remove(recipe); }

    public void setFavourites(Set<Recipe> favourites) { this.favourites = favourites; }
    public void addFavourite(Recipe recipe) { this.favourites.add(recipe); }
    public void deleteFavourite(Recipe recipe) { this.favourites.remove(recipe); }

    public boolean containsFavourite(Recipe recipe) { return this.favourites.contains(recipe); }

    public void setMeals(Set<Meal> meals) { this.meals = meals; }
    public void addMeal(Meal meal) { this.meals.add(meal); }
    public void deleteMeal(Meal meal) { this.meals.remove(meal); }
    public boolean containsMeal(Meal meal) { return this.meals.contains(meal); }
    public Set<Meal> getMeals() { return meals; }
}

