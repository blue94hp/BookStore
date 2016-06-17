package bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import bookstore.entity.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {

}
