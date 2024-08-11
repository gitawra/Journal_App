package tawra.technologies.Journals.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tawra.technologies.Journals.Entity.User;
import tawra.technologies.Journals.Services.UserServices;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserServices userServices;
    @GetMapping("health-check")
    public String healthChecks(){
        return "Ok_";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userServices.saveEncodedUser(user);
    }
}
