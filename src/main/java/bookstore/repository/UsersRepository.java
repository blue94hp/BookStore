package bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import bookstore.entity.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
	Users findByUsername(String username);
}
