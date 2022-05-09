package com.mkrana.recipe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mkrana.recipe.command.RecipeCommand;
import com.mkrana.recipe.converter.RecipeCommandToRecipe;
import com.mkrana.recipe.converter.RecipeToRecipeCommand;
import com.mkrana.recipe.domain.Difficulty;
import com.mkrana.recipe.domain.Notes;
import com.mkrana.recipe.domain.Recipe;
import com.mkrana.recipe.repositories.RecipeRepository;
import com.mkrana.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	private final RecipeCommandToRecipe recipeCommandToRecipe;

	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public List<Recipe> getAllRecipes() {
		List<Recipe> listOfRecipes = new ArrayList<>();
		recipeRepository.findAll().iterator().forEachRemaining(listOfRecipes::add);
		return listOfRecipes;
	}

	public Recipe createNewRecipe(Recipe freshBakedCookies) {
		freshBakedCookies.setDifficulty(Difficulty.EASY);
		Notes randomnote = new Notes();
		randomnote.setRecipeNotes("Recipe Note");
		freshBakedCookies.setNote(randomnote);
		return freshBakedCookies;
	}

	public Recipe findRecipeById(Long id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		return recipe.orElse(null);
	}

	@Override
	public RecipeCommand saveRecipe(RecipeCommand recipeCommand) {
		Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
		log.info(recipeCommand.toString());
		Recipe savedRecipe = recipeRepository.save(recipe);
		log.info("Recipe object saved");
		return recipeToRecipeCommand.convert(savedRecipe);
	}

}
