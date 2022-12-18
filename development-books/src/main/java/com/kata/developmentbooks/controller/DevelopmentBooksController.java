package com.kata.developmentbooks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevelopmentBooksController {

	@GetMapping("/getBookName")
	public String getBookName() {
		return "Clean Code";
	}
}
