package com.mkrana.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mkrana.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
