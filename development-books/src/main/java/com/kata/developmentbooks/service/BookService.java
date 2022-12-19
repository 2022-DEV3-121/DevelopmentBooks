package com.kata.developmentbooks.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kata.developmentbooks.enums.BooksEnum;
import com.kata.developmentbooks.exception.InvalidBookInputException;
import com.kata.developmentbooks.model.BookRequest;
import com.kata.developmentbooks.model.Books;
import com.kata.developmentbooks.model.PriceSummary;

@Service
public class BookService {

	private static final double SINGLE_BOOK_PRICE = 50.0;
	private static final double ONE_HUNDRED = 100;

	public List<Books> getAllBooks() {
		return Arrays.stream(BooksEnum.values()).map(bookEnum -> new Books(bookEnum.getId(), bookEnum.getTitle(),
				bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice())).collect(Collectors.toList());
	}

	public PriceSummary getPrice(List<BookRequest> books) {
		validateBookInputs(books);
		if (books.size() == 1) {
			return createPriceSummaryForOnlyOneBookType(books.get(0));
		} else {
			return calulateBooksPriceWithDiscount(books);
		}
	}

	public PriceSummary calulateBooksPriceWithDiscount(List<BookRequest> books) {
		int totalBooks = books.stream().mapToInt(book -> book.getQuantity()).sum();
		List<Integer> bookGroups = new ArrayList<Integer>();
		double priceOfSimilarBooksLeft = 0;
		int noOfGroups = 1 + (totalBooks / books.size());
		double finalPrice = 0;

		for (int i = 0; i < noOfGroups; i++) {
			int typesOfBookLeft = (int) books.stream().filter(book -> book.getQuantity() > 0).count();
			if (typesOfBookLeft > 1) {
				bookGroups.add(typesOfBookLeft);
				reduceQuantityOfAlreadyAddedBooksIntoGroups(books);
			} else {
				priceOfSimilarBooksLeft = calculatePriceForBooksWithoutDiscount(books);
				break;
			}
		}

		makeOptimalGroups(bookGroups);
		finalPrice = priceOfSimilarBooksLeft
				+ bookGroups.stream().mapToDouble(group -> calculateDiscountByNoOfTypesOfBooks(group)).sum();
		return createPriceSummary(totalBooks, finalPrice);
	}

	public PriceSummary createPriceSummary(int totalBooks, double finalPrice) {
		PriceSummary priceSummary = new PriceSummary();
		priceSummary.setActualPrice(SINGLE_BOOK_PRICE * totalBooks);
		priceSummary.setFinalPrice(finalPrice);
		priceSummary.setTotalBooks(totalBooks);
		priceSummary.setTotalDiscount(priceSummary.getActualPrice() - priceSummary.getFinalPrice());
		return priceSummary;
	}

	public double calculateDiscountByNoOfTypesOfBooks(int groupSize) {
		double discountedPrice = 0;
		double actualCost = groupSize * SINGLE_BOOK_PRICE;
		if (groupSize == 1)
			return SINGLE_BOOK_PRICE;
		if (groupSize == 2)
			return actualCost - (actualCost * (5.0 / ONE_HUNDRED));
		if (groupSize == 3)
			return actualCost - (actualCost * (10.0 / ONE_HUNDRED));
		if (groupSize == 4)
			return actualCost - (actualCost * (20.0 / ONE_HUNDRED));
		if (groupSize == 5)
			return actualCost - (actualCost * (25.0 / ONE_HUNDRED));
		return discountedPrice;
	}

	public PriceSummary createPriceSummaryForOnlyOneBookType(BookRequest booksInput) {
		double totalPrice = SINGLE_BOOK_PRICE * booksInput.getQuantity();
		PriceSummary priceSummary = new PriceSummary();
		priceSummary.setActualPrice(totalPrice);
		priceSummary.setFinalPrice(totalPrice);
		priceSummary.setTotalBooks(booksInput.getQuantity());
		priceSummary.setTotalDiscount(0);
		return priceSummary;
	}

	public void validateBookInputs(List<BookRequest> books) {
		List<Integer> availableBookIds = getAllBooks().stream().map(book -> book.getId()).collect(Collectors.toList());
		Map<Integer, Long> idCountsMap = books.stream().map(book -> book.getBookId())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		books.forEach(book -> {
			if (!availableBookIds.contains(book.getBookId())) {
				throw new InvalidBookInputException(
						"Invalid book Id provided, please select from the available book Id's only");
			}
			if (book.getQuantity() <= 0) {
				throw new InvalidBookInputException(
						"Invalid book quantity provided, please select quantity of book as more than one");
			}
		});

		if (idCountsMap.keySet().stream().anyMatch(bookId -> idCountsMap.get(bookId) > 1)) {
			throw new InvalidBookInputException(
					"Invalid book Id provided, please do not repeat any book Id which is already provided");
		}
	}

	public void reduceQuantityOfAlreadyAddedBooksIntoGroups(List<BookRequest> books) {
		books.forEach(book -> {
			book.setQuantity(book.getQuantity() - 1);
		});
	}

	public double calculatePriceForBooksWithoutDiscount(List<BookRequest> books) {
		return books.stream().filter(book -> book.getQuantity() > 0)
				.mapToDouble(book -> book.getQuantity() * SINGLE_BOOK_PRICE).sum();
	}

	public void makeOptimalGroups(List<Integer> groups) {
		for (int i = 0; i < groups.size(); i++) {
			Integer group = groups.get(i);
			if (group == 5 && groups.indexOf(3) != -1) {
				groups.set(i, 4);
				groups.set(groups.indexOf(3), 4);
			}
		}
	}
}
