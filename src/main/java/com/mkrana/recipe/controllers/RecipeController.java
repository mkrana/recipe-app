
package com.mkrana.recipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mkrana.recipe.command.RecipeCommand;
import com.mkrana.recipe.domain.Recipe;
import com.mkrana.recipe.exceptions.NotFoundException;
import com.mkrana.recipe.service.RecipeService;

@Controller
public class RecipeController {

	RecipeService recipeService;

	private static final String RECIPE = "recipe";

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{id}/show")
	public String getRecipeById(@PathVariable String id, Model model) {
		model.addAttribute(RECIPE, recipeService.findRecipeById(Long.valueOf(id)));
		return "recipe/show";
	}

	@GetMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute(RECIPE, new Recipe());
		return "recipe/newrecipe";
	}

	@PostMapping("/recipe/save")
	public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedEntity = recipeService.saveRecipe(recipeCommand);
		return "redirect:/recipe/" + savedEntity.getId() + "/show";
	}

	@GetMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		RecipeCommand savedRecipe = recipeService.findRecipeCommandById(Long.valueOf(id));
		model.addAttribute(RECIPE, savedRecipe);
		return "recipe/newrecipe";

	}

	@GetMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		recipeService.deleteRecipeById(Long.valueOf(id));
		return "redirect:/";

	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404notfound");
		modelAndView.addObject("exception", exception);
		return modelAndView;

	}

}
