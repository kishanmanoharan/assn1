package com.comp3095.assn1.model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public UserController(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/signin")
    public String getSignIn(Model model) {
        model.addAttribute(new User());
        return "account/signin";
    }
    @PostMapping("/signin")
    public String postSignIn(User user, BindingResult result) {
        if (result.hasErrors()) {
            return "account/signin";
        }
        else {
//            User user = new User(model.getAttribute("email").toString(), model.getAttribute("pass").toString());
            User dbUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (dbUser != null) {
                return "redirect:/" + dbUser.getId();
            }
            return "redirect:/signin";
        }
    }

    @GetMapping("/{userId}")
    public String getHome(@PathVariable("userId") Integer userId, Model model) {

        if (userRepository.existsById(userId)) {
            User user = userRepository.getUserById(userId);
            model.addAttribute("user", user);
            List<Recipe> recipes = recipeRepository.findByUser(user);
            model.addAttribute("recipes", recipes);
            return "account/home";
        }
        return "account/signin";
    }

    @GetMapping("/signup")
    public String getSignUp(Model model) {
        model.addAttribute(new User());
        return "account/signup";
    }
    @PostMapping("/signup")
    public String postSignUp(User user, BindingResult result) {
        if (result.hasErrors()) {
            return "account/signup";
        }
        else {
            userRepository.save(user);
            return "redirect:/signin";
        }
    }

    @GetMapping("/{userId}/newrecipe")
    public String getNewRecipe(@PathVariable("userId") Integer userId, Model model) {
        return "account/home";h
    }

    @GetMapping("/info")
    public String getInfo(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("recipes", recipeRepository.findAll());
        return "account/info";
    }
}
