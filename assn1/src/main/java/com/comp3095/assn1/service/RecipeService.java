package com.comp3095.assn1.service;
import com.comp3095.assn1.model.Recipe;
import com.comp3095.assn1.model.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> listAll(String search) {
        if (search != null) {
            return recipeRepository.search(search);
        }
        return (List<Recipe>) recipeRepository.findAll();
    }
}
