package com.example.cs458project4;

import com.example.cs458project4.Models.Alert;
import com.example.cs458project4.Models.Suser;
import com.example.cs458project4.Models.Symptom;
import com.example.cs458project4.Repository.AlertRepository;
import com.example.cs458project4.Repository.SuserRepository;
import com.example.cs458project4.Repository.SymptomRepository;
import com.example.cs458project4.User.UserService;
import com.example.cs458project4.dto.InfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Cs458project4ApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private SuserRepository suserRepository;

    @Autowired
    private SymptomRepository symptomRepository;
    @Autowired
    private AlertRepository alertRepository;

    @Test
    void contextLoads() {
        assert userService != null;
    }

    private Suser testUser = Suser.builder()
            .fullName("testuser")
            .TCNumber("123999")
            .build();

    void setup() {
        suserRepository.save(testUser);

        Suser testUserWithId = suserRepository.findByTCNumber("123999");

        symptomRepository.saveAll(List.of(
            Symptom.builder().suser_fk(testUserWithId.getId()).symptom("hapsirik").build()
        ));
    }

    void clearUp() {
        Suser testUserWithId = suserRepository.findByTCNumber("123999");

        Optional<Symptom> symptom = userService.findSymptomForUser(testUserWithId.getId(), "hapsirik");

        symptomRepository.delete(symptom.get());

        suserRepository.delete(testUser);
    }

    @Test
    void findByTcNumberWorks() {
        setup();

        assert userService.findByTCNumber("123999") != null;

        clearUp();
    }

    @Test
    void findByIdWorks() {
        Suser user = userService.createSuser("123998", "pass");

        Suser foundByTc = userService.findByTCNumber("123998");
        Optional<Suser> foundById = userService.findById(foundByTc.getId());

        assert foundById.isPresent();

        assert foundById.get().getTCNumber().equals("123998");

        // Clear up
        suserRepository.delete(user);
    }

    @Test
    void createUserWorks() {
        Suser user = userService.createSuser("123998", "pass");

        assert user != null;
        assert user.getTCNumber().equals(("123998"));

        // Clear up
        suserRepository.delete(user);
    }

    @Test
    void getUserWorks() {
        Suser user = userService.createSuser("123998", "pass");

        Suser foundByTc = userService.findByTCNumber("123998");
        Optional<Suser> foundById = userService.getUser(foundByTc.getId());

        assert foundById.isPresent();

        assert foundById.get().getTCNumber().equals("123998");


        // Clear up
        suserRepository.delete(user);
    }

    @Test
    void createSymptomWorks() {
        suserRepository.save(testUser);
        Long userId = testUser.getId();
        userService.createSymptom(userId, "olum");

        Symptom foundSymptom = symptomRepository.findBysymptom("olum");

        assert foundSymptom != null;
        assert foundSymptom.getSuser_fk().equals(userId);

        symptomRepository.delete(foundSymptom);
        suserRepository.delete(testUser);
    }

    @Test
    void createSymptomWorksAlertWorks() {
        try {
            suserRepository.save(testUser);
            userService.createSymptom(testUser.getId(), "olum");

            Symptom s = userService.findSymptomForUser(testUser.getId(), "olum").get();
            s.setCount(3);
            userService.updateSymptom(s, testUser.getId());
            List<Alert> alerts = userService.getAllAlerts(testUser.getId());
            System.out.println("======\n\n" + alerts.size() + "\n\n=======\n\n");
            assert alerts.size() == 1;
            assert alerts.get(0).getSymptomId().equals(s.getId());

        } finally {
            List<Alert> alerts = userService.getAllAlerts(testUser.getId());
            Symptom s = userService.findSymptomForUser(testUser.getId(), "olum").get();
            suserRepository.delete(testUser);
            symptomRepository.delete(s);
            alertRepository.delete(alerts.get(0));
        }
    }

    @Test
    void findSymptomForUserWorks() {
        setup();
        String s1 = "hapsirik";
        Suser suser = userService.findByTCNumber("123999");
        Optional<Symptom> symptom1 = userService.findSymptomForUser(suser.getId(), s1);
        assert s1.equals(symptom1.get().getSymptom());
        clearUp();
    }

    @Test
    void createAlertWorks(){
        Alert alert = new Alert(16L, 112L, "alert!");
        userService.createAlert(alert);

        assert alertRepository.findById(alert.getId()) != null;

        alertRepository.deleteById(alert.getId());
    }

    @Test
    void deleteAlertWorks(){
        Alert alert = new Alert(16L, 112L, "alert!");
        alertRepository.save(alert);
        userService.deleteAlert(alert.getId());

        assert alertRepository.findById(alert.getId()).isPresent() == false;
    }

    @Test
    void updateUserWorks(){
        suserRepository.save(testUser);
        Long userId = suserRepository.findById(testUser.getId()).get().getId();
        InfoDTO dto = new InfoDTO(userId, "test", "25", "male", "170", "55");
        Suser nUser = userService.updateUser(dto);

        assert nUser.getFullName().equals("test") && nUser.getAge().equals("25") &&
                nUser.getGender().equals("male") && nUser.getHeight().equals("170") && nUser.getWeight().equals("55");
        suserRepository.delete(testUser);
    }

    @Test
    void getAllAlertsWorks(){
        Symptom testSymptom = null;
        Alert testAlert = null;
        try {
            suserRepository.save(testUser);

            testSymptom = Symptom.builder()
                    .suser_fk(testUser.getId())
                    .symptom("Bilkent")
                    .build();

            symptomRepository.save(testSymptom);


            testAlert = new Alert(testSymptom.getId(), testUser.getId(), "");

            alertRepository.save(testAlert);

            // Test
            List<Alert> allAlerts = userService.getAllAlerts(testUser.getId());
            System.out.println("==================\n\n");
            System.out.println(allAlerts);
            System.out.println(testSymptom.getId());
            System.out.println("==================\n\n");
            assert allAlerts.size() == 1;

            assert allAlerts.get(0).getSymptomId().equals(testSymptom.getId());
        } finally {
            alertRepository.delete(testAlert);

            symptomRepository.delete(testSymptom);

            suserRepository.delete(testUser);
        }
    }
}
