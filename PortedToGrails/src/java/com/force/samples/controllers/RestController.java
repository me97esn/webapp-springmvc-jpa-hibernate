package com.force.samples.controllers;

import javax.inject.Inject;

import com.force.samples.dao.AuthorDAO;
import com.force.samples.entity.Author;
import com.force.samples.services.BookService;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.force.samples.dao.BookDAO;
import com.force.samples.entity.Book;

import java.io.IOException;


@Controller
@RequestMapping(value="/rest")
public class RestController {
	
	private static Logger log = LoggerFactory.getLogger(RestController.class);

    @Inject
    BookService bookService;

	@Inject
	private BookDAO bookDAO;

    @Inject
	private AuthorDAO authorDAO;

	@RequestMapping(method=RequestMethod.GET, value="/book/{bookId}")
	public @ResponseBody Book getBook (@PathVariable(value="bookId") long bookId, Model model) {
		
		log.info("Searching for book with id = " + bookId);
		
		Book book = bookDAO.getBookById(bookId);
		return book;
	}

    @RequestMapping(method=RequestMethod.PUT, value="/book", headers="Accept=application/json")
	public @ResponseBody Book createBook (@RequestBody String body, Model model) throws IOException {

        System.out.println("createOrReplaceBook:" + body);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonBook = mapper.readTree( body );

        bookService.createBook(jsonBook.get("title").toString(),
                jsonBook.get("author").get("firstname").toString(),
                jsonBook.get("author").get("lastname").toString());

		return null;
	}
}
