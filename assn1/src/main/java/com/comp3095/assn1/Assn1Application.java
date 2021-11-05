package com.comp3095.assn1;

import com.comp3095.assn1.model.Recipe;
import com.comp3095.assn1.model.RecipeRepository;
import com.comp3095.assn1.model.User;
import com.comp3095.assn1.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Assn1Application {

	public static void main(String[] args) {
		SpringApplication.run(Assn1Application.class, args);
	}

//	@Bean
//	public CommandLineRunner mappingDemo(UserRepository userRepository,
//										 RecipeRepository recipeRepository) {
//		return args -> {
//
//			// create a new user
//			User user = new User("test", "test");
//
//			// save the user
//			userRepository.save(user);
//
//			// create and save new recipes
//			recipeRepository.save(new Recipe("Introduction", user));
//			recipeRepository.save(new Recipe("Java 8", user));
//			recipeRepository.save(new Recipe("Concurrency", user));
//
//			userRepository.findAll();
//			recipeRepository.findAll();
//		};
//	}


}
