package com.mkrana.recipe.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mkrana.recipe.command.RecipeCommand;
import com.mkrana.recipe.domain.Recipe;
import com.mkrana.recipe.service.RecipeService;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	@InjectMocks
	RecipeController recipeController;

	MockMvc controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	@Test
	void testGetRecipeById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		when(recipeService.findRecipeById(anyLong())).thenReturn(recipe);
		controller.perform(get("/recipe/1/show")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void testNewRecipeForm() throws Exception {

		controller.perform(get("/recipe/new")).andExpect(status().isOk()).andExpect(model().attributeExists("recipe"))
				.andExpect(view().name("recipe/newrecipe"));
	}

	@Test
	void testSaveRecipeForm() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(2L);
		when(recipeService.saveRecipe(any())).thenReturn(recipeCommand);
		controller
				.perform(post("/recipe/save").accept(MediaType.APPLICATION_FORM_URLENCODED).param("id", "2")
						.param("description", "Bakedgoods"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/recipe/2/show"));

	}

	@Test
	void testUpdateRecipe() throws Exception {
		when(recipeService.findRecipeCommandById(any())).thenReturn(new RecipeCommand());
		controller.perform(get("/recipe/1/update")).andExpect(status().is(200))
				.andExpect(view().name("recipe/newrecipe")).andExpect(model().attributeExists("recipe"));

	}

	@Test
	void testDeletRecipe() throws Exception {
		controller.perform(get("/recipe/1/delete")).andExpect(view().name("redirect:/"))
				.andExpect(status().is3xxRedirection());
		verify(recipeService).deleteRecipeById(anyLong());
	}

}
