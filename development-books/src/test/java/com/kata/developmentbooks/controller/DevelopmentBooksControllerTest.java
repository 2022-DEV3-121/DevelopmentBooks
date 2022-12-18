package com.kata.developmentbooks.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kata.developmentbooks.service.BookService;

class DevelopmentBooksControllerTest {

	DevelopmentBooksController controller;

	@BeforeEach
	public void setUp() {
		controller = new DevelopmentBooksController();
		controller.service = new BookService();
	}

	@Test
	void getBookNameShouldReturnNameOfTheBook() {
		List<String> result = controller.getAllBooks();
		assertEquals("Clean Code", result.get(0));
	}

	@Test
	public void getAllBooksShouldReturnFiveBookNames() {
		List<String> books = controller.getAllBooks();
		assertEquals(5, books.size());
		assertTrue(books.containsAll(getAllBooks()));
	}

	private List<String> getAllBooks() {
		return Arrays.asList("Clean Code", "The Clean Coder", "Clean Architecture",
				"Test Driven Development by Example", "Working Effectively With Legacy Code");
	}

}
