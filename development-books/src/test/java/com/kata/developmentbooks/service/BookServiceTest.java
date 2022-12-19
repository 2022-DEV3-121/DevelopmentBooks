package com.kata.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kata.developmentbooks.model.BookRequest;
import com.kata.developmentbooks.model.Books;

class BookServiceTest {

	BookService service;

	@BeforeEach
	public void setUp() {
		service = new BookService();
	}

	@Test
	public void getAllBooksShouldReturnFiveBookNames() {
		List<Books> books = service.getAllBooks();
		assertEquals(5, books.size());
		assertTrue(books.containsAll(getExpectedBooks()));
	}

	@Test
	public void getAllBooksShouldReturnBooksWithDetails() {
		List<Books> result = service.getAllBooks();
		List<Books> excpectedBooks = getExpectedBooks();
		assertEquals(excpectedBooks.get(0), result.get(0));
		assertEquals(excpectedBooks.get(1), result.get(1));
		assertEquals(excpectedBooks.get(2), result.get(2));
		assertEquals(excpectedBooks.get(3), result.get(3));
		assertEquals(excpectedBooks.get(4), result.get(4));
	}

	@Test
	public void getPriceShouldReturnSuccessMessage() {
		double result = service.getPrice(new BookRequest(1, 1));
		assertEquals(50.0, result);
	}

	private List<Books> getExpectedBooks() {
		List<Books> excpectedBooks = new ArrayList<Books>();
		excpectedBooks.add(new Books(1, "Clean Code", "Robert Martin", 2008, 50.00));
		excpectedBooks.add(new Books(2, "The Clean Coder", "Robert Martin", 2011, 50.00));
		excpectedBooks.add(new Books(3, "Clean Architecture", "Robert Martin", 2017, 50.00));
		excpectedBooks.add(new Books(4, "Test-Driven Development By Example", "Kent Beck", 2003, 50.00));
		excpectedBooks.add(new Books(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.00));
		return excpectedBooks;
	}

}
