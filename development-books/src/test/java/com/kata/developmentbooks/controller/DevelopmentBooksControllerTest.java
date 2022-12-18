package com.kata.developmentbooks.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DevelopmentBooksControllerTest {

	@Test
	void getBookNameShouldReturnNameOfTheBook() {
		DevelopmentBooksController controller = new DevelopmentBooksController();
		String result = controller.getBookName();
		assertEquals("Clean Code", result);
	}

}
