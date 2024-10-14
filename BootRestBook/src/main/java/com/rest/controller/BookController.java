package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entities.Book;
import com.rest.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> list = bookService.getAllBooks();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id) {
		Book book = this.bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}

	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		try {
			Book b = this.bookService.addBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).body(b);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/book/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id) {
		if (bookService.deleteBook(id) == true) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<String> updateBook(@RequestBody Book book, @PathVariable int id) {
		if (bookService.updateBook(book, id) == true) {
			return ResponseEntity.ok("Book updated successfully.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
