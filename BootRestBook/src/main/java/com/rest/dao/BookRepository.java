package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	public Book findById(int id);

}
