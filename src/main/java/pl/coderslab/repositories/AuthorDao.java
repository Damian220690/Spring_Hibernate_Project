package pl.coderslab.repositories;

import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveAuthor(Author author){
        entityManager.persist(author);
    }

    public void updateAuthor(Author author){
        entityManager.merge(author);
    }

    public Author findAuthorById(long id){
        return entityManager.find(Author.class, id);
    }

    public void deleteAuthor(long id){
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }
}
