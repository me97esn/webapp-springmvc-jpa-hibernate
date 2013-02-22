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

    public Book create(Book b) {
        entityManager.persist(b);
        return b;
    }
}
