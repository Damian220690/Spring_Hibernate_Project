package pl.coderslab.repositories;

import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDao {

    @PersistenceContext
    EntityManager entityManager;

    public void addPerson(Person person) {
        entityManager.persist(person);
    }

    public Person findPersonById(long id) {
        return entityManager.find(Person.class, id);
    }

    public void removePerson(long id){
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(entityManager.contains(person)? person : entityManager.merge(person));
    }

    public Person updatePerson(Person person){
        return entityManager.merge(person);
    }


}
