package com.example.cs458project4.User;

import com.example.cs458project4.Models.Alert;
import com.example.cs458project4.Models.Suser;
import com.example.cs458project4.Models.Symptom;
import com.example.cs458project4.dto.AlertDTO;
import com.example.cs458project4.dto.InfoDTO;
import com.example.cs458project4.dto.SuserDTO;
import com.example.cs458project4.dto.SymptomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/user")
public class UserController {
    // Dependency Injection
    private final UserService userService;

    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/login")
    public String login(@RequestBody SuserDTO request) {

        Suser check = userService.findByTCNumber(request.getTcnumber());
        if(check == null || !check.getPassword().equals(request.getPassword())) {

            return "No user";
        }
        return check.getId().toString();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Suser signup(@RequestBody SuserDTO request) {
        System.out.println("ALL: =========" + request);
        System.out.println("TCnumber: " + request.getTcnumber() + "password: " + request.getPassword());
        Suser check = userService.findByTCNumber(request.getTcnumber());
        if(check != null){
            return null;
        }
        Suser newSuser = userService.createSuser(request.getTcnumber(), request.getPassword());

        return newSuser; //return the id
    }

    @PostMapping(path ="/info")
    @CrossOrigin(origins = "http://localhost:3000")
    public Suser info(@RequestBody InfoDTO request){
        Suser s = userService.updateUser(request);
        return s;
    }

    @GetMapping(path ="/info")
    @CrossOrigin(origins = "http://localhost:3000")
    public Suser info(@RequestParam Long userId){
        Optional<Suser> s = userService.getUser(userId);
        return s.get();
    }

    @PostMapping(path ="/disease")
    public String disease(@RequestBody String all, Long id, String disease){
        userService.createDisease(id, disease);

        return "";
    }


    @PostMapping(path ="/symptom")
    public Suser symptoms(@RequestBody SymptomDTO request){
        Long id = request.getId(); //user id
        Optional<Suser> s = userService.findById(id);
        Optional<Symptom> symptom = userService.findSymptomForUser(id, request.getSymptom());
        if(symptom.isPresent()){
            symptom.get().setCount(symptom.get().getCount() + 1);
            userService.updateSymptom(symptom.get());
        }
        else{
            userService.createSymptom(id, request.getSymptom());
        }
        return s.get();
    }

    @GetMapping(path ="/alert")
    public List<Alert> getAlerts(@RequestParam Long id){
        return userService.getAllAlerts(id);
    }

    @PostMapping(path ="/alert")
    public void createAlert(@RequestBody AlertDTO request){
        System.out.println("======\n\n======" + request.getAlert());
        userService.createAlert(request.getAlert());
        //return userService.getAllAlerts(request.getAlert().getUserId());
    }
    @DeleteMapping(path ="/alert")
    public void deleteAlert(@RequestParam Long alertId){

        userService.deleteAlert(alertId);

    }
}
