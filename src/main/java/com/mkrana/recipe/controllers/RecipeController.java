package com.mkrana.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkrana.recipe.service.RecipeService;

@Controller()
@RequestMapping(path = "/recipe")
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping("/show/{id}")
	public String getRecipeById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
		return "recipe/show";
	}

}
