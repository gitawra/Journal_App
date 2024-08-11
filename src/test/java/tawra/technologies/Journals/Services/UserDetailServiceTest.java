package tawra.technologies.Journals.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import tawra.technologies.Journals.Repository.UserRepository;




public class UserDetailServiceTest {
    @InjectMocks
    private UserDetailsServices userDetailsServices;

    @Mock
    private UserRepository userRepository;

    @Disabled
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @Disabled
    void loadUserByUserNameTest(){
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().username("sam").password("asasasasass").build());
        UserDetails user = userDetailsServices.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
