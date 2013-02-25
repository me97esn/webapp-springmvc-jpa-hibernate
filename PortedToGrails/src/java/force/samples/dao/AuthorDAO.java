package com.force.samples.dao;

import com.force.samples.entity.Author;
import com.force.samples.entity.Book;

import java.util.List;

public interface AuthorDAO {
    public List<Author> getAllAuthors();

    public Author getAuthorByName(String firstname, String lastname);

    public Author create(Author author);
}
