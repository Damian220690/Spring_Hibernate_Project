package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entities.Author;
import pl.coderslab.entities.Book;
import pl.coderslab.entities.Publisher;
import pl.coderslab.repositories.BookDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/books", produces = "text/plain;charset=UTF-8")
public class BookController {

    @Autowired
    BookDao bookDao;

    @GetMapping("/addBook")
    @ResponseBody
    public String addBook(){
        Book book = new Book("Hobbit", 10.0,"fantastic story");
        book.setAuthors(Arrays.asList(new Author("J.R.R", "Tolkien"),new Author("Christopher","Tolkien")));
        book.setPublisher(new Publisher("Zysk i S-ka"));
        bookDao.saveBook(book);
        return "The book has been added!!!";
    }

    @GetMapping("/editBook/{id}")
    @ResponseBody
    public String editBook(@PathVariable long id){
        Book editedBook = bookDao.findBookById(id);

        Publisher editedPublisher = editedBook.getPublisher();
        editedPublisher.setName("Helion");
        editedBook.setPublisher(editedPublisher);

        List<Author> authors = editedBook.getAuthors();
        Author author1 = authors.get(0);
        author1.setFirstName("Mark");
        author1.setLastName("Lutz");
        Author author2 = authors.get(1);
        author2.setFirstName("David");
        author2.setLastName("Ascher");
        editedBook.setAuthors(authors);

        editedBook.setTitle("Python - Wprowadzenie");
        editedBook.setDescription("very interesting book");
        editedBook.setRating(7.8);
        bookDao.updateBook(editedBook);
        return "The book has been edited!!!";
    }

    @GetMapping("/findBook/{id}")
    @ResponseBody
    public String findBookById(@PathVariable long id){
        Book book = bookDao.findBookById(id);
        return book.toString();
    }

    @GetMapping("/removeBook/{id}")
    @ResponseBody
    public String removeBook(@PathVariable long id) {
        bookDao.deleteBook(id);
        return "The book has been removed";
    }

}
