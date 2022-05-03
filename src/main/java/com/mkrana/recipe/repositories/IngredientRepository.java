package com.mkrana.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkrana.recipe.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
