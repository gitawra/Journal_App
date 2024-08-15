package tawra.technologies.Journals.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    @Disabled
    public void testSaveNewUser(){
        Assertions.assertNotNull(userRepositoryImpl.getUserForSa());
    }
}
