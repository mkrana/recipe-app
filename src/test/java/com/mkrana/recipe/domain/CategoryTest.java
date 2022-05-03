package com.mkrana.recipe.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryTest {

	Category category = new Category();

	@Test
	void testGetId() {
		Long initialvalue = 4L;
		category.setId(initialvalue);
		assertEquals(initialvalue, category.getId());
	}

	@Test
	void testGetDescription() {
	}

	@Test
	void testGetRecipies() {
	}

}
