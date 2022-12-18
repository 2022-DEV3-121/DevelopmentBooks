package com.kata.developmentbooks.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.kata.developmentbooks.enums.BooksEnum;

@Service
public class BookService {

	public List<String> getAllBooks() {
		return Stream.of(BooksEnum.values()).map(Enum::name).collect(Collectors.toList());
	}
}
