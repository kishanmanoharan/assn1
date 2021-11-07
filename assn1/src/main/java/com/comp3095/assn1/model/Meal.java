package com.comp3095.assn1.model;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @OneToOne
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Meal() { this.date = LocalDate.now(); };

    public Meal(LocalDate date, User user, Recipe recipe) {
        this.date = date;
        this.recipe = recipe;
    }

    public Integer getId() { return id; }
    public LocalDate getDate() { return date; }
    public Recipe getRecipe() { return recipe; }
    public User getUser() { return this.user; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
    public void setUser(User user) { this.user = user; }

}


