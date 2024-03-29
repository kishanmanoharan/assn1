// -----------------------------------
//  Project: Recipes App
//  Assignment: Assignment 1
//  Author: Kishan Manoharan, Jeff Mcilveen
//  Student numbers: 101266708, 100681357
//  Date: 11/07/2021
//  Description: Main Application
//------------------------------------

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

}
