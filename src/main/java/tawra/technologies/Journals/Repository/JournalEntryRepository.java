package tawra.technologies.Journals.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import tawra.technologies.Journals.Entity.JournalEntery;

public interface JournalEntryRepository extends MongoRepository<JournalEntery, ObjectId>{
}
