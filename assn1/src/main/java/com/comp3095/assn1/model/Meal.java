package com.comp3095.assn1.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    public Meal() {};

    public Meal(Date date, Time time, User user, Recipe recipe) {
        this.date = date;
        this.time = time;
        this.user = user;
        this.recipe = recipe;
    }

    public Integer getId() { return id; }
    public Date getDate() { return date; }
    public Time getTime() { return time; }
    public User getUser() { return user; }
    public Recipe getRecipe() { return recipe; }

}
