package tawra.technologies.Journals.Services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tawra.technologies.Journals.Entity.JournalEntery;
import tawra.technologies.Journals.Entity.User;
import tawra.technologies.Journals.Repository.JournalEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserServices userServices;

    @Transactional // If there is any exception it will rol back the earlier code also.
    public void saveJournalEntry(JournalEntery journalEntery, String userName){
        try {
            User user = userServices.findByUserName(userName);
            journalEntery.setDate(LocalDateTime.now());
            JournalEntery saved = journalEntryRepository.save(journalEntery);
            user.getJournalEnteryList().add(saved);
            userServices.saveUser(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving an entry"+ e);
        }
    }
    public void saveJournalEntry(JournalEntery journalEntery){
        journalEntryRepository.save(journalEntery);
    }

    public List<JournalEntery> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntery> findById(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    @Transactional
    public boolean deleteById(ObjectId myId, String userName){
        boolean removed = false;
        try {
            User user = userServices.findByUserName(userName);
            removed = user.getJournalEnteryList().removeIf(x -> x.getId().equals(myId));
            if(removed){
                userServices.saveUser(user);
                journalEntryRepository.deleteById(myId);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry: "+e);
        }
    return removed;
    }
}
