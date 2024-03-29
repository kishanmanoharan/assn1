// -----------------------------------
//  Project: Recipes App
//  Assignment: Assignment 1
//  Author: Kishan Manoharan, Jeff Mcilveen
//  Student numbers: 101266708, 100681357
//  Date: 11/07/2021
//  Description: Main Controller for routing
//------------------------------------

package com.comp3095.assn1.model;
import com.comp3095.assn1.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final MealRepository mealRepository;

    public UserController(UserRepository userRepository, RecipeRepository recipeRepository, MealRepository mealRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
    }

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/signin")
    public String getSignIn(Model model) {
        model.addAttribute(new User());
        model.addAttribute("error", false);
        return "account/signin";
    }
    @PostMapping("/signin")
    public String postSignIn(User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            User dbUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (dbUser != null) {
                return "redirect:/" + dbUser.getId();
            }
        }
        model.addAttribute("error", true);
        return "account/signin";
    }

    @GetMapping("/{userId}")
    public String getHome(@PathVariable("userId") Integer userId, Model model) {
        if (userRepository.existsById(userId)) {
            User user = userRepository.getUserById(userId);
            model.addAttribute("user", user);
            List<Recipe> recipes = recipeRepository.findByUser(user);
            model.addAttribute("recipes", recipes);
            Set<Recipe> favourites = user.getFavourites();
            model.addAttribute("favourites", favourites);
            Set<Meal> meals = user.getMeals();
            model.addAttribute("meals", meals);
            return "account/home";
        }
        return "redirect:/signin";
    }

    @GetMapping("/signup")
    public String getSignUp(Model model) {
        model.addAttribute(new User());
        model.addAttribute("error", false);
        return "account/signup";
    }
    @PostMapping("/signup")
    public String postSignUp(User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (userRepository.findByUsername(user.getUsername()) == null) {
                userRepository.save(user);
                return "redirect:/signin";
            }
            model.addAttribute("error", true);
        }
        return "account/signup";
    }

    @GetMapping("/{userId}/newrecipe")
    public String getNewRecipe(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("userId", userId);
        model.addAttribute(userRepository.getUserById(userId));
        return "recipe/new";
    }
    @PostMapping("/{userId}/newrecipe")
    public String postNewRecipe(@PathVariable("userId") Integer userId, Recipe recipe, BindingResult result) {
        if (result.hasErrors()) {
            return "recipe/new";
        }
        else {
            User user = userRepository.getUserById(userId);
            if (user != null) {
                recipe.setUser(user);
            }
            recipeRepository.save(recipe);
            return "redirect:/" + user.getId();
        }
    }
    @GetMapping("/{userId}/view/{recipeId}")
    public String getViewRecipe(@PathVariable("userId") Integer userId, @PathVariable("recipeId") Integer recipeId, Model model) {
        if (recipeRepository.existsById(recipeId) && userRepository.existsById(userId)) {
            Recipe recipe = recipeRepository.getRecipesById(recipeId);
            model.addAttribute("recipe", recipe);
            User user = userRepository.getUserById(userId);
            model.addAttribute("user", user);
            model.addAttribute("userId", userId);
        }
        return "recipe/view";
    }

    @GetMapping("/{userId}/delete/{recipeId}")
    public String getDeleteRecipe(@PathVariable("userId") Integer userId, @PathVariable("recipeId") Integer recipeId, Model model) {
        if (recipeRepository.existsById(recipeId) && userRepository.existsById(userId)) {
            Recipe recipe = recipeRepository.getRecipesById(recipeId);
            model.addAttribute("recipe", recipe);
            User user = userRepository.getUserById(userId);
            model.addAttribute("user", user);
            model.addAttribute("userId", userId);
        }
        return "recipe/delete";
    }

    @PostMapping("/{userId}/delete/{recipeId}")
    public String postDeleteRecipe(@PathVariable("userId") Integer userId, @PathVariable("recipeId") Integer recipeId, Recipe recipe, User user, BindingResult result) {
        if (!result.hasErrors()) {
            user = userRepository.getUserById(userId);
            recipe = recipeRepository.getRecipesById(recipeId);
            if (user.getId() ==  recipe.getUser().getId()) {
                List<User> users = (List<User>) userRepository.findAll();
                for (User dbUser:users) {
                    if (dbUser.containsFavourite(recipe)) {
                        dbUser.deleteFavourite(recipe);
                        userRepository.save(dbUser);
                    }
                }
                recipeRepository.delete(recipe);
                user.deleteRecipes(recipe);
                userRepository.save(user);
            }
        }
        return "redirect:/" + userId.toString();
    }

    @GetMapping("/{userId}/newmeal/{recipeId}")
    public String getNewMeal(@PathVariable("userId") Integer userId, @PathVariable("recipeId") Integer recipeId, User user, Recipe recipe, Model model) {
        user = userRepository.getUserById(userId);
        recipe = recipeRepository.getRecipesById(recipeId);
        model.addAttribute("recipe", recipe);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        model.addAttribute("meal", new Meal());
        return "meal/new";
    }

    @PostMapping("/{userId}/newmeal/{recipeId}")
    public String postNewMeal(@PathVariable("userId") Integer userId, @PathVariable("recipeId") Integer recipeId, Meal meal, User user, Recipe recipe, BindingResult result) {
        if (!result.hasErrors()) {
            user = userRepository.getUserById(userId);
            recipe = recipeRepository.getRecipesById(recipeId);
            if (user != null && recipe != null) {
                meal.setRecipe(recipe);
                meal.setUser(user);
                mealRepository.save(meal);
                return "redirect:/" + userId.toString();
            }
        }
        return "meal/new";
    }

//    @RequestMapping(value = "/{userId}/deletemeal/{mealId}", method = RequestMethod.POST)
//    public String postDeleteMeal(@PathVariable("userId") Integer userId, @PathVariable("mealId") Integer mealId, User user, Model model) {
//        user = userRepository.getUserById(userId);
//
//        if (user != null){
////            Meal meal = user.con
////            user.deleteMeal(meal);
//            userRepository.save(user);
//        }
//        return "redirect:/" + userId.toString();
//    }

    @RequestMapping("/{userId}/search")
    public String getSearchRecipe(@PathVariable("userId") Integer userId, User user, @Param("search") String search, Model model) {
        user = userRepository.getUserById(userId);
        model.addAttribute("recipes", recipeService.listAll(search));
        model.addAttribute("search", search);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        return "recipe/search";
    }

    @RequestMapping(value = "/{userId}/fav/{recipeId}", method = RequestMethod.POST)
    public String postToggleFavouriteRecipe(@PathVariable("userId") Integer userId, @PathVariable("recipeId") Integer recipeId, Recipe recipe, User user) {
        user = userRepository.getUserById(userId);
        recipe = recipeRepository.getRecipesById(recipeId);
        if (user.containsFavourite(recipe)) {
            user.deleteFavourite(recipe);
        }
        else {
            user.addFavourite(recipe);
        }
        userRepository.save(user);
        return "redirect:/" + userId.toString();
     }

//    Used ONLY for testing
    @GetMapping("/info")
    public String getInfo(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("meals", mealRepository.findAll());
        return "account/info";
    }
}
