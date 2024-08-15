package tawra.technologies.Journals.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tawra.technologies.Journals.Cache.AppCache;
import tawra.technologies.Journals.Entity.JournalEntery;
import tawra.technologies.Journals.Entity.User;
import tawra.technologies.Journals.Enums.Sentiments;
import tawra.technologies.Journals.Repository.UserRepositoryImpl;
import tawra.technologies.Journals.Services.EmailService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
//    @Scheduled(cron = "0 * * ? * *")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRepositoryImpl.getUserForSa();
        for(User user: users){
            List<JournalEntery> journalEnteryList = user.getJournalEnteryList();
            List<Sentiments> sentiments = journalEnteryList.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiments()).collect(Collectors.toList());
            Map<Sentiments, Integer> sentimentCounts = new HashMap<>();
            for(Sentiments sentiments1: sentiments){
                if(sentiments1 != null){
                    sentimentCounts.put(sentiments1,sentimentCounts.getOrDefault(sentiments1,0) + 1);
                }
                Sentiments mostFrequentSentiments = null;
                int maxCount = 0;
                for(Map.Entry<Sentiments, Integer> entry : sentimentCounts.entrySet()){
                    if(entry.getValue() > maxCount){
                        maxCount = entry.getValue();
                        mostFrequentSentiments = entry.getKey();
                    }
                }

                if(mostFrequentSentiments != null){
                    emailService.sendMail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiments.toString());
                }
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}
