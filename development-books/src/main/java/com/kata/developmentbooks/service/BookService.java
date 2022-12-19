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
		double actualCost = totalBooks * SINGLE_BOOK_PRICE;
		double discount = 0;
		if (typesOfBook == 2 ) {
			discount = 5;
		}
		if(typesOfBook == 3) {
			discount = 10;
		}
		if(typesOfBook == 4) {
			discount = 20;
		}
		double finalPrice = actualCost - (actualCost * (discount / 100));
        return createPriceSummary(totalBooks, finalPrice);
    }

    public PriceSummary createPriceSummary(int totalBooks, double finalPrice) {
        PriceSummary priceSummary = new PriceSummary();
        priceSummary.setActualPrice(50 * totalBooks);
        priceSummary.setFinalPrice(finalPrice);
        priceSummary.setTotalBooks(totalBooks);
        priceSummary.setTotalDiscount(priceSummary.getActualPrice() - priceSummary.getFinalPrice());
        return priceSummary;
	}
}
