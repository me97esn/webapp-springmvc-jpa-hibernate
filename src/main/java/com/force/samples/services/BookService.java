package com.force.samples.services;

import com.force.samples.dao.AuthorDAO;
import com.force.samples.dao.BookDAO;
import com.force.samples.entity.Author;
import com.force.samples.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Service
public class BookService {

    @PersistenceContext
    EntityManager entityManager;


    @Inject
    private BookDAO bookDAO;

    @Inject
    private AuthorDAO authorDAO;

    public Book createBook(String title, String firstname, String lastname) {
        Author author = authorDAO.getAuthorByName(firstname, lastname);
        if (author == null) {
            author = new Author();
            author.setFirstName(firstname);
            author.setLastName(lastname);

            entityManager.persist(author);
        }

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);

        bookDAO.create( book );

        return book;
    }
}
