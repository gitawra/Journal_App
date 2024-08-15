package tawra.technologies.Journals.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config-journal-app")
@Data
public class ConfigJournalAppEntity {
    private String key;
    private String value;
}
