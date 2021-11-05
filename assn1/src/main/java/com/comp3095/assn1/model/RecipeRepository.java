package com.comp3095.assn1.model;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    Recipe getRecipesById(Integer id);


    List<Recipe> findByUser(User user);
    List<Recipe> findByUser(User user, Sort sort);
}
