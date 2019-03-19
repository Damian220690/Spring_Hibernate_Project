package pl.coderslab.repositories;

import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void savePublisher(Publisher publisher){
        entityManager.persist(publisher);
    }

    public void updatePublisher(Publisher publisher){
        entityManager.merge(publisher);
    }

    public Publisher findPublisherById(long id){
        return entityManager.find(Publisher.class, id);
    }

    public void deletePublisher(long id){
        Publisher publisher = entityManager.find(Publisher.class, id);
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }
}
