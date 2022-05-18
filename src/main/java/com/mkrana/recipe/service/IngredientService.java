package com.mkrana.recipe.service;

import com.mkrana.recipe.command.IngredientCommand;

public interface IngredientService {
	
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	IngredientCommand saveOrUpdateIngredient(IngredientCommand ingredientCommand);
	
	void deleteIngredient(Long recipeId, Long ingredientId);
	

}
