package tawra.technologies.Journals.Services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tawra.technologies.Journals.Repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testAdd(){
        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("ram"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "7,3,4",
            "5,4,3"
    })
    @Disabled
    @Test
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }
}
