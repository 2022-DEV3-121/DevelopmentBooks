package com.kata.developmentbooks.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kata.developmentbooks.enums.BooksEnum;
import com.kata.developmentbooks.model.BookRequest;
import com.kata.developmentbooks.model.Books;

@Service
public class BookService {

	public List<Books> getAllBooks() {
		return Arrays.stream(BooksEnum.values()).map(bookEnum -> new Books(bookEnum.getId(), bookEnum.getTitle(),
				bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice())).collect(Collectors.toList());
	}

	public double getPrice(BookRequest bookRequest) {
		return getAllBooks().stream().filter(book -> book.getId() == bookRequest.getBookId()).findAny().get()
				.getPrice();
	}
}
