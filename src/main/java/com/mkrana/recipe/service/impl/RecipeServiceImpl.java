package com.mkrana.recipe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mkrana.recipe.domain.Difficulty;
import com.mkrana.recipe.domain.Notes;
import com.mkrana.recipe.domain.Recipe;
import com.mkrana.recipe.repositories.RecipeRepository;
import com.mkrana.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public List<Recipe> getAllRecipes() {
		List<Recipe> listOfRecipes = new ArrayList<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(recipe -> listOfRecipes.add(recipe));
		return listOfRecipes;
	}

	public Recipe createNewRecipe(Recipe freshBakedCookies) {
		freshBakedCookies.setDifficulty(Difficulty.EASY);
		Notes randomnote = new Notes();
		randomnote.setRecipeNotes("Recipe Note");
		freshBakedCookies.setNote(randomnote);
		return freshBakedCookies;
	}

}
