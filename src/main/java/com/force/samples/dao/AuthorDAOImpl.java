package com.force.samples.dao;

import com.force.samples.entity.Author;
import com.force.samples.entity.Book;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class AuthorDAOImpl extends JpaDaoSupport implements AuthorDAO {

    @PersistenceContext
    EntityManager entityManager;


    public List<Author> getAllAuthors() {
        return entityManager.createQuery("from " + Author.class)
                .getResultList();
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

    public Author create(Author author) {
        System.out.println("Creating the autor");

        entityManager.persist(author);
        return author;
    }
}
