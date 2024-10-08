package tawra.technologies.Journals.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tawra.technologies.Journals.ApiResponce.WeatherResponse;
import tawra.technologies.Journals.Entity.User;
import tawra.technologies.Journals.Repository.UserRepository;
import tawra.technologies.Journals.Services.UserServices;
import tawra.technologies.Journals.Services.WeatherService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userServices.findByUserName(username);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userServices.saveEncodedUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepository.deleteByUserName(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherRes = weatherService.getWeather("Delhi");
        String greeting = "";
        if(weatherRes != null){
            greeting = ", Weather feels like "+ weatherRes.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+ authentication.getName() + greeting, HttpStatus.OK);
    }

}
