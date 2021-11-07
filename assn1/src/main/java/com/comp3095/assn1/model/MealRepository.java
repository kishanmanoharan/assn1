// -----------------------------------
//  Project: Recipes App
//  Assignment: Assignment 1
//  Author: Kishan Manoharan, Jeff Mcilveen
//  Student numbers: 101266708, 100681357
//  Date: 11/07/2021
//  Description: Meal DB Repository
//------------------------------------

package com.comp3095.assn1.model;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Integer> {

    Meal getMealById(Integer id);

    Meal findMealById(Integer id);
}
