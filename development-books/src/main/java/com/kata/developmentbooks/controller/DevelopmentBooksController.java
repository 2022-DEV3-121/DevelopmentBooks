package com.kata.developmentbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.developmentbooks.service.BookService;

@RestController
public class DevelopmentBooksController {

	@Autowired
	BookService service;

	@GetMapping("/getAllBooks")
	public List<String> getAllBooks() {
		return service.getAllBooks();
	}
}
