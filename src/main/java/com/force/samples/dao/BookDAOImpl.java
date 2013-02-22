package com.force.samples.dao;

import java.util.List;

import com.force.samples.entity.Author;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.force.samples.entity.Book;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class BookDAOImpl extends JpaDaoSupport implements BookDAO {
    @PersistenceContext
    EntityManager entityManager;

    public List<Book> getAllBooks() {
        return entityManager.createQuery("from Book")
                .getResultList();
    }

    public List<Book> getBooksByTitle(String title) {
        return entityManager.createQuery("from Author where title=:title")
                .setParameter("title", title)
                .getResultList();
    }

    public Book getBookById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public Author getAuthorByName(String firstname, String lastname) {
        List<Author> authors = entityManager.createQuery("from Author where firstname=:first and lastname=:last ")
                .setParameter("first", firstname)
                .setParameter("last", lastname)
                .setMaxResults(1)
                .getResultList();

        if (authors.size() > 0) {
            System.out.println("Found author in db");
            return authors.get(0);
        } else {
            return null;
        }

    }


    public Book create(String title, String firstname, String lastname) {

        Author author = getAuthorByName(firstname, lastname);
        if(author == null){
            author = new Author();
            author.setFirstName(firstname);
            author.setLastName(lastname);

            entityManager.persist(author);
        }

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);

        System.out.println("Creating the book");
        entityManager.persist(book);

        return book;
    }

}
