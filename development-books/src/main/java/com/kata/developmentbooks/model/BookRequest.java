package com.kata.developmentbooks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookRequest {

	private int bookId;

	private int quantity;

}
