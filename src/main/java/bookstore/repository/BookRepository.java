package bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import bookstore.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
