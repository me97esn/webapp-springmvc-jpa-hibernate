package com.force.samples.dao;

import com.force.samples.entity.Author;
import com.force.samples.entity.Book;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl extends JpaDaoSupport implements AuthorDAO {

    @PersistenceContext
    EntityManager entityManager;

	public List<Author> getAllAuthors() {
		return getJpaTemplate().find("select b from Author b");
	}

    /**
     * Returns the first author with this name
     * @param name
     * @return
     */
	public Author getAuthorByName(String name) {
        System.out.println("TODO implement this method");
		return null;
	}

    public Author create(String name) {
        System.out.println("Creating a new Author");

        return null;
    }
}
