Overview

A pricing model for buying developer books from the collection where you can get discounts when you buy more than one. These are the widly essential books from where a can select -

* Clean Code (Robert Martin, 2008)
* The Clean Coder (Robert Martin, 2011)
* Clean Architecture (Robert Martin, 2017)
* Test Driven Development by Example (Kent Beck, 2003)
* Working Effectively With Legacy Code (Michael C. Feathers, 2004)

Rules For Buying

One copy of the five books costs 50 EUR.

* If, however, you buy two different books from the series, you get a 5% discount on those two books.
* If you buy 3 different books, you get a 10% discount.
* With 4 different books, you get a 20% discount.
* If you go for the whole hog, and buy all 5, you get a huge 25% discount.
* Note that if you buy, say, 4 books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the 4th book still costs 50 EUR.


Tools used for developing this application

Java Version - 8

Junit version - 4

spring boot version - 2.7.6

build tool - maven 3.6.1


Functional case
* If the shopping basket contains the below books.
* 2 copies of the “Clean Code” book
* 2 copies of the “Clean Coder” book
* 2 copies of the “Clean Architecture” book
* 1 copy of the “Test Driven Development by Example” book
* 1 copy of the “Working effectively with Legacy Code” book
* We can avail the discounts for above shopping basket containing 8 books by grouping [5,3] or [4,4] or [3,3,2], etc. Output should be 320 EUR as the best price by applying [4,4] as below.
* (4 * 50 EUR) - 20% [first book, second book, third book, fourth book]
* (4 * 50 EUR) - 20% [first book, second book, third book, fifth book]
* = (160 EUR + 160 EUR) => 320 EUR

Purpose
* Develop a application to calculate the best price of any conceivable shopping basket by applicable the above discounts using Test Driven Development (TDD).

How to build the application
* Clone this repository
* https://github.com/2022-DEV3-121/DevelopmentBooks
* You can build the project and run the tests by running mvn clean install

How to run the application
* By default the application will start on port 8080. If you want the application to run on different port like 8081 you can update the server.port in application.properties.

How to send input
* To get all available books with details use GET Method with mapping /getAllBooks.
* To calculate book price use POST Method with mapping /getPrice and send request body as bookId and quantity of books.

[{
	"bookId" : 1,
	"quantity" : 2
},{
	"bookId" : 2,
	"quantity" : 2
},
]

