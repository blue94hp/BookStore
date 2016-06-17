package bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import bookstore.entity.PurchaseHistory;

public interface PurchaseHistoryRepository extends MongoRepository<PurchaseHistory, String>{

}
