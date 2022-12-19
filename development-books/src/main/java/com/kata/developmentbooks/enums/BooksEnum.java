package com.kata.developmentbooks.enums;

public enum BooksEnum {
	CLEAN_CODE(1, "Clean Code", "Robert Martin", 2008, 50.00),
	THE_CLEAN_CODER(2, "The Clean Coder", "Robert Martin", 2011, 50.00),
	CLEAN_ARCHITECTURE(3, "Clean Architecture", "Robert Martin", 2017, 50.00),
	TEST_DRIVEN_DEVELOPMENT(4, "Test-Driven Development By Example", "Kent Beck", 2003, 50.00),
	WORKING_WITH_LEGACY_CODE(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.00);

	private int id;
	private String title;
	private String author;
	private int year;
	private double price;

	BooksEnum(int id, String title, String author, int year, double price) {
		this.setId(id);
		this.setTitle(title);
		this.setAuthor(author);
		this.setYear(year);
		this.setPrice(price);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	private void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	private void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	private void setPrice(double price) {
		this.price = price;
	}
}
