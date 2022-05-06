package com.mkrana.recipe.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mkrana.recipe.domain.Notes;
import com.mkrana.recipe.domain.Recipe;
import com.mkrana.recipe.repositories.RecipeRepository;

class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	Recipe recipe;

	@Captor
	ArgumentCaptor<Notes> noteArgument;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	void test() {
		Recipe recipe = new Recipe();
		Set<Recipe> recipeSet = new HashSet<>();
		recipeSet.add(recipe);
		when(recipeRepository.findAll()).thenReturn(recipeSet);
		assertEquals(recipeService.getAllRecipes().size(), 1);
		verify(recipeRepository, times(1)).findAll();

	}

	@Test
	void createNewRecipeTest() {
		recipeService.createNewRecipe(recipe);
		verify(recipe).setNote(noteArgument.capture());
		Notes note = noteArgument.getValue();
		assertEquals("Recipe Note", note.getRecipeNotes());
	}

	@Test
	void getRecipeByIdTest() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
		Recipe recipeReturned = recipeService.findRecipeById(1L);
		assertNotNull(recipeReturned);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();

	}

}
