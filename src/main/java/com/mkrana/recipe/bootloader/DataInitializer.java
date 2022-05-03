package com.mkrana.recipe.bootloader;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mkrana.recipe.domain.Category;
import com.mkrana.recipe.domain.Difficulty;
import com.mkrana.recipe.domain.Ingredient;
import com.mkrana.recipe.domain.Notes;
import com.mkrana.recipe.domain.Recipe;
import com.mkrana.recipe.domain.UnitOfMeasure;
import com.mkrana.recipe.repositories.CategoryRepository;
import com.mkrana.recipe.repositories.RecipeRepository;
import com.mkrana.recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

	private final CategoryRepository categoryRepository;

	private final UnitOfMeasureRepository unitOfMeasureRepository;

	private final RecipeRepository recipeRepository;

	public DataInitializer(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			RecipeRepository recipeRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		populateRecipe();
	}

	private void populateRecipe() {
		log.info("Invoked populateRecipe()");

		Optional<UnitOfMeasure> large = unitOfMeasureRepository.findByUom("Large");
		Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByUom("Cup");
		Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByUom("Teaspoon");

		Optional<Category> banana = categoryRepository.findByDescription("Banana");
		Optional<Category> breakfast = categoryRepository.findByDescription("Breakfast");
		Optional<Category> easyBaking = categoryRepository.findByDescription("Easy Baking");
		Set<Category> categories = new HashSet<>();
		categories.add(banana.get());
		categories.add(breakfast.get());
		categories.add(easyBaking.get());

		// Creating Chocolate Banana Bread Recipe
		Recipe breadRecipe = new Recipe();
		breadRecipe.setCategories(categories);
		breadRecipe.setCookTime(60);
		breadRecipe.setPrepTime(15);
		breadRecipe.setServings(12);
		breadRecipe.setDescription("Chocolate Banana Bread");
		breadRecipe.setNote(new Notes(
				"Use very ripe or over-ripe bananas. The peels should be at least half browned, and the bananas inside squishy and browning.\n"
						+ "Do not use Dutch processed cocoa for this recipe, only natural unsweetened.\n" + "\n"
						+ "Melted coconut oil can be used in place of the butter. The flavor will change a little and you may get a hint of coconut in the result.\n"
						+ "There is only 1 egg and 1 teaspoon of baking soda in this recipe for leavening, so make sure your baking soda is still good!"));
		breadRecipe.setDirections("Prepare pan and preheat oven:\n"
				+ "Butter or spray with cooking spray the inside of a 5x9-inch loaf pan. Preheat oven to 350°F (175°C) with a rack in the middle.\n"
				+ "\n" + "Purée bananas:\n"
				+ "Using a blender, food processor, or a fork, purée the peeled ripe bananas until smooth. You should have 1 1/2 to 1 3/4 cups of banana purée.\n"
				+ "\n" + "\n" + "Mix wet ingredients and sugar:\n"
				+ "Place the banana purée into a large mixing bowl. Stir the melted butter. Stir in the brown sugar and salt. Whisk to break up any clumps of brown sugar. Stir in the beaten egg and vanilla extract.\n"
				+ "\n" + "Mix dry ingredients:\n"
				+ "In a separate bowl, vigorously whisk together the flour, cocoa, baking soda, and allspice.\n" + "\n"
				+ "Add the flour mixture to the banana mixture:\n"
				+ "Stir until just incorporated, then stir in the chocolate chips.\n" + "\n" + "Bake:\n"
				+ "Pour batter into a buttered loaf pan. Place in a 350°F (175°C) oven and bake for 1 hour.\n" + "\n"
				+ "Note that because of the chocolate chips that melt in the bread as it cooks, it's hard to check for doneness using a tester that you insert.\n"
				+ "\n"
				+ "One way to test is to gently push the center top of the chocolate banana bread down with your finger. If it feels relatively firm and bounces back when you release your finger, that's a good sign that it's done.\n"
				+ "\n" + "Let cool completely:\n" + "Remove from oven and let cool for 10 minutes in the pan.\n" + "\n"
				+ "Gently remove the loaf from the pan and place on a rack to cool completely.\n" + "\n"
				+ "Slice with a serrated bread knife to serve.");
		breadRecipe.setDifficulty(Difficulty.MEDIUM);
		breadRecipe.addIngredients(new Ingredient("Banana", new BigDecimal(3), large.get()));
		breadRecipe.addIngredients(new Ingredient("Butter", new BigDecimal(1 / 3), cup.get()));
		breadRecipe.addIngredients(new Ingredient("Sugar", new BigDecimal(3 / 4), cup.get()));
		breadRecipe.addIngredients(new Ingredient("Salt", new BigDecimal(1), teaspoon.get()));
		breadRecipe.addIngredients(new Ingredient("Egg Beaten", new BigDecimal(1), large.get()));
		recipeRepository.save(breadRecipe);

	}

}
