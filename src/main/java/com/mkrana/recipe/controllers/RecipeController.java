package com.mkrana.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkrana.recipe.command.RecipeCommand;
import com.mkrana.recipe.domain.Recipe;
import com.mkrana.recipe.service.RecipeService;

@Controller()
@RequestMapping(path = "/recipe")
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping("/{id}/show")
	@GetMapping
	public String getRecipeById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
		return "recipe/show";
	}

	@GetMapping
	@RequestMapping("/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		return "recipe/newrecipe";
	}

	@PostMapping
	@RequestMapping(path = "/save")
	public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedEntity = recipeService.saveRecipe(recipeCommand);
		return "redirect:/recipe/" + savedEntity.getId() + "/show";
	}

	@GetMapping
	@RequestMapping("/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		RecipeCommand savedRecipe = recipeService.findRecipeCommandById(Long.valueOf(id));
		model.addAttribute("recipe", savedRecipe);
		return "recipe/newrecipe";

	}

	@GetMapping
	@RequestMapping("/{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		recipeService.deleteRecipeById(Long.valueOf(id));
		return "redirect:/";

	}

}
