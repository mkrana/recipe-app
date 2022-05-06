package com.mkrana.recipe.service;

import java.util.List;

import com.mkrana.recipe.domain.Recipe;

public interface RecipeService {

	public List<Recipe> getAllRecipes();

	public Recipe findRecipeById(Long id);

}
