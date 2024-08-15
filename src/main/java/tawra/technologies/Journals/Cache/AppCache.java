package tawra.technologies.Journals.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tawra.technologies.Journals.Entity.ConfigJournalAppEntity;
import tawra.technologies.Journals.Repository.ConfigJournalAppRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public Map<String, String> APP_CACHE;
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;
    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity: all){
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
