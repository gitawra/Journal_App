package tawra.technologies.Journals.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import tawra.technologies.Journals.Entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
    void deleteByUserName(String username);

}
