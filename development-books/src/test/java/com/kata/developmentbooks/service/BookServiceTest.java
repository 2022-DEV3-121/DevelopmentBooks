package com.kata.developmentbooks.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kata.developmentbooks.model.BookRequest;
import com.kata.developmentbooks.model.Books;
import com.kata.developmentbooks.model.PriceSummary;

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
	public void getPriceShouldReturnPriceOfBooks() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(2, 1));
        PriceSummary result = service.getPrice(books);
		assertEquals(95.0, result.getFinalPrice());
	}
	
    @Test
    public void getPriceShouldReturnFivePercentDiscountedPriceForTwoDiffBooks() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(2, 1));
        PriceSummary result = service.getPrice(books);
        assertEquals(95.0, result.getFinalPrice());
    }
    
    @Test
    public void getPriceShouldReturnTenPercentDiscountedPriceForThreeDiffBooks() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(2, 1));
        books.add(new BookRequest(3, 1));
        PriceSummary result = service.getPrice(books);
        assertEquals(135.0, result.getFinalPrice(), 0.0);
    }
    
    @Test
    public void getPriceShouldReturnTwentyPercentDiscountedPriceForFourDiffBooks() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(2, 1));
        books.add(new BookRequest(3, 1));
        books.add(new BookRequest(4, 1));
        PriceSummary result = service.getPrice(books);
        assertEquals(160.0, result.getFinalPrice(), 0.0);
    }
    
    @Test
    public void getPriceShouldReturnTwentyFivePercentDiscountedPriceForFiveDiffBooks() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(2, 1));
        books.add(new BookRequest(3, 1));
        books.add(new BookRequest(4, 1));
        books.add(new BookRequest(5, 1));
        PriceSummary result = service.getPrice(books);
        assertEquals(187.5, result.getFinalPrice());
    }
    
    @Test
    public void getPriceShouldReturnActualPriceWithoutDiscountedForMultipleBooksOfSameType() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 5));
        PriceSummary result = service.getPrice(books);
        assertEquals(250.0, result.getFinalPrice());
    }
    
    @Test
    public void getPriceShouldThrowInputMismatchExceptionIfInvalidBookIdProvided() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(6, 1));
        InputMismatchException exception = assertThrows(InputMismatchException.class, () -> {
			service.getPrice(books);
		});
		String expectedMessage = "Invalid book Id provided, please select from the available book Id's only";
		assertTrue(exception.getMessage().equals(expectedMessage));
    }
    
    @Test
    public void getPriceShouldThrowInputMismatchExceptionIfInvalidQuantityIsProvided() {
        List<BookRequest> books = new ArrayList<BookRequest>();
        books.add(new BookRequest(1, 1));
        books.add(new BookRequest(5, 0));
        InputMismatchException exception = assertThrows(InputMismatchException.class, () -> {
			service.getPrice(books);
		});
		String expectedMessage = "Invalid book quantity provided, please select quantity of book as more than one";
		assertTrue(exception.getMessage().equals(expectedMessage));
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
