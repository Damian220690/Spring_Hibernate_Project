package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entities.Book;
import pl.coderslab.repositories.BookDao;

@Controller
@RequestMapping(value = "/books", produces = "text/plain;charset=UTF-8")
public class BookController {

    @Autowired
    BookDao bookDao;

    @GetMapping("/addBook/{publisher}")
    @ResponseBody
    public String addBook(@PathVariable String publisher){
        Book book = new Book("Hobbit", "Tolkien", 8.9,publisher,"very interesting book");
        bookDao.saveBook(book);
        return "The book has been added!!!";
    }

    @GetMapping("/editBook/{id}")
    @ResponseBody
    public String editBook(@PathVariable long id){
        Book editedBook = bookDao.findBookById(id);
        editedBook.setPublisher("Greg");
        editedBook.setDescription("fantastic story");
        editedBook.setRating(9.6);
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
    public String removeBook(@PathVariable long id){
        bookDao.deleteBook(id);
        return "The book has been removed";
    }

}
