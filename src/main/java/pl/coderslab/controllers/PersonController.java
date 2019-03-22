package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entities.Person;
import pl.coderslab.entities.PersonDetails;
import pl.coderslab.repositories.PersonDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonDao personDao;

    @GetMapping("/addPerson/{login}/{password}/{email}")
    @ResponseBody
    public String addPerson(@PathVariable String login, @PathVariable String password, @PathVariable String email){
        Person person = new Person(login,password,email);
        PersonDetails personDetails = new PersonDetails("Katarzyna", "Malinowska","Nowa","8","Warszawa");
        person.setPersonDetails(personDetails);
        personDao.addPerson(person);
        return "The Person has been added!!!";
    }

    @GetMapping("/editPerson/{id}")
    @ResponseBody
    public String editPerson(@PathVariable long id){
        Person person = personDao.findPersonById(id);

        PersonDetails personDetails = person.getPersonDetails();
        personDetails.setFirstName("Dariusz");
        personDetails.setLastName("Kruczkowski");
        personDetails.setCity("Kocmyrzów");
        personDetails.setStreet("Piaskowa");
        personDetails.setStreetNumber("18");
        person.setPersonDetails(personDetails);

        person.setLogin("Sandro90");
        person.setEmail("DDD@gmail.com");

        personDao.updatePerson(person);
        return  "The person has been edited!!!";
    }


    @GetMapping("/findPerson/{id}")
    @ResponseBody
    public String  findPersonById(@PathVariable long id){
        Person person = personDao.findPersonById(id);
        return person.toString();
    }

    @GetMapping("/removePerson/{id}")
    @ResponseBody
    public String removePerson(@PathVariable long id){
        personDao.removePerson(id);
        return "The Person has been removed";
    }


}
