package bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import bookstore.entity.UserRoles;

public interface UserRolesRepository extends MongoRepository<UserRoles, String> {

}
