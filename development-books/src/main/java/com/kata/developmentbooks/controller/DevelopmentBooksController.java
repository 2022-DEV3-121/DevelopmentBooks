package com.kata.developmentbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kata.developmentbooks.model.BookRequest;
import com.kata.developmentbooks.model.Books;
import com.kata.developmentbooks.service.BookService;

@RestController
public class DevelopmentBooksController {

	@Autowired
	BookService service;

	@GetMapping("/getAllBooks")
	public List<Books> getAllBooks() {
		return service.getAllBooks();
	}

	@PostMapping("/getPrice")
	public double getPrice(@RequestBody BookRequest bookRequest) {
		return service.getPrice(bookRequest);
	}
}
