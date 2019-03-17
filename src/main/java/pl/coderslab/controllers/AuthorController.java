package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entities.Author;
import pl.coderslab.repositories.AuthorDao;

@Controller
@RequestMapping(value = "/authors", produces = "text/plain;charset=UTF-8")
public class AuthorController {

    @Autowired
    AuthorDao authorDao;

    @GetMapping("/addAuthor/{firstName}/{lastName}")
    @ResponseBody
    public String addBook(@PathVariable String firstName, @PathVariable String lastName){
        Author author = new Author(firstName, lastName);
        authorDao.saveAuthor(author);
        return "The author has been added!!!";
    }

    @GetMapping("/editAuthor/{id}")
    @ResponseBody
    public String editAuthor(@PathVariable long id){
        Author editedAuthor = authorDao.findAuthorById(id);
        editedAuthor.setFirstName("Henryk");
        editedAuthor.setLastName("Sienkiewicz");
        authorDao.updateAuthor(editedAuthor);
        return "The book has been edited!!!";
    }

    @GetMapping("/findAuthor/{id}")
    @ResponseBody
    public String findAuthorById(@PathVariable long id){
        Author author = authorDao.findAuthorById(id);
        return author.toString();
    }

    @GetMapping("/removeAuthor/{id}")
    @ResponseBody
    public String removeBook(@PathVariable long id){
        authorDao.deleteAuthor(id);
        return "The author has been removed";
    }

}
