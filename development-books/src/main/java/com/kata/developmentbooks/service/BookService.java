package com.kata.developmentbooks.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kata.developmentbooks.enums.BooksEnum;
import com.kata.developmentbooks.model.BookRequest;
import com.kata.developmentbooks.model.Books;
import com.kata.developmentbooks.model.PriceSummary;

@Service
public class BookService {

	private static final double SINGLE_BOOK_PRICE = 50.0;

	public List<Books> getAllBooks() {
		return Arrays.stream(BooksEnum.values()).map(bookEnum -> new Books(bookEnum.getId(), bookEnum.getTitle(),
				bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice())).collect(Collectors.toList());
	}

	public PriceSummary getPrice(List<BookRequest> books) {
		int totalBooks = books.stream().mapToInt(book -> book.getQuantity()).sum();
        int typesOfBook = books.size();
        return createPriceSummary(totalBooks, calculateDiscountByNoOfTypesOfBooks(totalBooks, typesOfBook));
    }

    public PriceSummary createPriceSummary(int totalBooks, double finalPrice) {
        PriceSummary priceSummary = new PriceSummary();
        priceSummary.setActualPrice(50 * totalBooks);
        priceSummary.setFinalPrice(finalPrice);
        priceSummary.setTotalBooks(totalBooks);
        priceSummary.setTotalDiscount(priceSummary.getActualPrice() - priceSummary.getFinalPrice());
        return priceSummary;
	}
    
    public double calculateDiscountByNoOfTypesOfBooks(int totalBooks, int typesOfBook) {
		double discountedPrice = 0;
		double actualCost = totalBooks * SINGLE_BOOK_PRICE;
		if (typesOfBook == 1)
			discountedPrice = 50;
		if (typesOfBook == 2)
			discountedPrice = actualCost - (actualCost * (5.0 / 100));
		if (typesOfBook == 3)
			discountedPrice = actualCost - (actualCost * (10.0 / 100));
		if (typesOfBook == 4)
			discountedPrice = actualCost - (actualCost * (20.0 / 100));
		if (typesOfBook == 5)
			discountedPrice = actualCost - (actualCost * (25.0 / 100));
		return discountedPrice;
	}
}
