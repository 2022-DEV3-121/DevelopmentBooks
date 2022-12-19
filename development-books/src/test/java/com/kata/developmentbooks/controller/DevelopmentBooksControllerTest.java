package com.kata.developmentbooks.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kata.developmentbooks.model.BookRequest;
import com.kata.developmentbooks.model.Books;
import com.kata.developmentbooks.model.PriceSummary;
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
		List<Books> result = controller.getAllBooks();
		assertEquals("Clean Code", result.get(0).getTitle());
	}

	@Test
	public void getAllBooksShouldReturnFiveBookNames() {
		List<Books> books = controller.getAllBooks();
		assertEquals(5, books.size());
	}

	@Test
	public void getPriceShouldReturnPriceOfBooks() {
		List<BookRequest> books = new ArrayList<BookRequest>();
		books.add(new BookRequest(1, 1));
		books.add(new BookRequest(2, 1));
		PriceSummary result = controller.getPrice(books);
		assertEquals(95.0, result.getFinalPrice());
	}

}
