package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entities.Publisher;

@Controller
@RequestMapping(value = "/publishers", produces = "text/plain;charset=UTF-8")
public class PublisherController {
    @Autowired
    PublisherDao publisherDao;

    @GetMapping("/addPublisher/{name}")
    @ResponseBody
    public String addPublisher(@PathVariable String name){
        Publisher publisher = new Publisher(name);
        publisherDao.savePublisher(publisher);
        return "The publisher has been added!!!";
    }

    @GetMapping("/editPublisher/{id}")
    @ResponseBody
    public String editPublisher(@PathVariable long id){
        Publisher editedpublisher = publisherDao.findPublisherById(id);
        editedpublisher.setName("PWN");
        publisherDao.updatePublisher(editedpublisher);
        return "The publisher has been edited!!!";
    }

    @GetMapping("/findPublisher/{id}")
    @ResponseBody
    public String findPublisherById(@PathVariable long id){
        Publisher publisher = publisherDao.findPublisherById(id);
        return publisher.toString();
    }

    @GetMapping("/removePublisher/{id}")
    @ResponseBody
    public String removePublisher(@PathVariable long id){
        publisherDao.deletePublisher(id);
        return "The publisher has been removed";
    }


}
