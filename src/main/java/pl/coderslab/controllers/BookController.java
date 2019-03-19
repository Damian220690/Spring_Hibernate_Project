package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entities.Book;
import pl.coderslab.entities.Publisher;
import pl.coderslab.repositories.BookDao;

@Controller
@RequestMapping(value = "/books", produces = "text/plain;charset=UTF-8")
public class BookController {

    @Autowired
    BookDao bookDao;

    @GetMapping("/addBook")
    @ResponseBody
    public String addBook(){
        Book book = new Book("Hobbit", 8.9,"very interesting book");
        book.setAuthor("Tolkien");
        book.setPublisher(new Publisher("ABC"));
        bookDao.saveBook(book);
        return "The book has been added!!!";
    }

    @GetMapping("/editBook/{id}")
    @ResponseBody
    public String editBook(@PathVariable long id){
        Book editedBook = bookDao.findBookById(id);
        editedBook.setPublisher(new Publisher("Greg"));
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
