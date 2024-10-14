package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.BookRepository;
import com.rest.entities.Book;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		List<Book> list = (List<Book>) this.bookRepository.findAll();
		return list;
	}

	public Book getBookById(int id) {
		Book book = bookRepository.findById(id);
		return book;
	}

	public Book addBook(Book b) {
		bookRepository.save(b);
		return b;
	}

	public boolean deleteBook(int id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean updateBook(Book book, int id) {
		if (bookRepository.existsById(id)) {
			book.setId(id);
			bookRepository.save(book);
			return true;
		}
		return false;
	}

}
