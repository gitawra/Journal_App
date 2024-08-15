package tawra.technologies.Journals.Services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Disabled
    void testSendMail(){
        emailService.sendMail("sanshaytawra.777@gmail.com", "Testing JAVA mail sender.", "Hey, ap kaise h !");
    }
}
