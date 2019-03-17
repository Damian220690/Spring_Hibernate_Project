package pl.coderslab.repositories;

import org.springframework.stereotype.Repository;
import pl.coderslab.entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveBook(Book book){
        entityManager.persist(book);
    }

    public void updateBook(Book book){
        entityManager.merge(book);
    }

    public Book findBookById(long id){
        return entityManager.find(Book.class, id);
    }

    public void deleteBook(long id){
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }
}
