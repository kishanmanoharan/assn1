package com.comp3095.assn1.model;

import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Integer> {

    Meal getMealById(Integer id);

//    Meal findmealById(Integer id);
}
