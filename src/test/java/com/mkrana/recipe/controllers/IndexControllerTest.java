
package com.mkrana.recipe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.mkrana.recipe.domain.Recipe;
import com.mkrana.recipe.service.RecipeService;

class IndexControllerTest {

	IndexController indexController;

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	@Captor
	ArgumentCaptor<List<Recipe>> argumentRecipeCaptor;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		indexController = new IndexController(recipeService);
	}

	@Test
	void testGetIndexPage() {

		// given
		Recipe recipe = new Recipe();
		Recipe secondRecipe = new Recipe();
		secondRecipe.setId(2L);
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe);
		recipes.add(secondRecipe);

		// when
		when(recipeService.getAllRecipes()).thenReturn(recipes);

		assertEquals("index", indexController.getIndexPage(model));
		verify(recipeService, times(1)).getAllRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentRecipeCaptor.capture());

		// Argument Captor
		assertEquals(2, argumentRecipeCaptor.getValue().size());
	}

	@Test
	void mockMvcTest() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));

	}

}
