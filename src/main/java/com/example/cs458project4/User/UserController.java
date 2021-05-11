package com.example.cs458project4.User;

import com.example.cs458project4.Models.Suser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/user")

public class UserController {
    // Dependency Injection
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody String all, String TCNumber, String password) {
        Suser check = userService.findByTCNumber(TCNumber);
        if(check == null){
            return "No user with given credentials";
        }
        return check.getId().toString();
    }

    @PostMapping(path = "/signup")
    public String signup(@RequestBody String all, String TCNumber, String password) {
        Suser check = userService.findByTCNumber(TCNumber);
        if(check != null){
            return "User already exists";
        }
        Suser newSuser = userService.createSuser(TCNumber, password);
        return newSuser.getId().toString(); //return the id
    }

    @PostMapping(path ="/info")
    public String info(@RequestBody String all, Long id, String fullName, String gender, String dateOfBirth, String age, String height, String weight){

        return "";
    }

}
