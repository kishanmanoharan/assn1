package com.comp3095.assn1.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String date;

    private int recipeId;


    public Meal() {};

    public Meal(String date, User user, Integer recipeId) {
        this.date = date;
        this.recipeId = recipeId;
    }

    public Integer getId() { return id; }
    public String getDate() { return date; }
    public Integer getRecipe() { return recipeId; }

    public void setDate(String date) { this.date = date; }
    public void setRecipe(Integer recipe) { this.recipeId = recipeId; }
}
