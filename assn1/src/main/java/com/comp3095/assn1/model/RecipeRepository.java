package com.comp3095.assn1.model;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    Recipe getRecipesById(Integer id);


    List<Recipe> findByUser(User user);
    List<Recipe> findByUser(User user, Sort sort);

    List<Recipe> findByNameOrderByName(String name);

    List<Recipe> findByName(String name);

    List<Recipe> findByNameContaining(String containing);

    @Query("SELECT r FROM Recipe r WHERE r.name LIKE %?1%")
    public List<Recipe> search(String search);

    List<Recipe> findAllByNameIsLike(String name);

}
