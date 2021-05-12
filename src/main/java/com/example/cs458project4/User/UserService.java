package com.example.cs458project4.User;

import com.example.cs458project4.Models.Alert;
import com.example.cs458project4.Models.ChronicDisease;
import com.example.cs458project4.Models.Suser;
import com.example.cs458project4.Models.Symptom;
import com.example.cs458project4.Repository.AlertRepository;
import com.example.cs458project4.Repository.ChronicDiseaseRepository;
import com.example.cs458project4.Repository.SuserRepository;
import com.example.cs458project4.Repository.SymptomRepository;
import com.example.cs458project4.dto.InfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class UserService {
    @Autowired
    private SuserRepository repo;
    @Autowired
    private ChronicDiseaseRepository crepo;
    @Autowired
    private SymptomRepository srepo;
    @Autowired
    private AlertRepository arepo;

    public UserService(SuserRepository repo, ChronicDiseaseRepository crepo, SymptomRepository srepo, AlertRepository arepo) {
        this.repo = repo;
        this.crepo = crepo;
        this.srepo = srepo;
        this.arepo = arepo;
    }

    public Suser findByTCNumber(String TCNumber){
        Suser obj = repo.findByTCNumber(TCNumber);

        return obj;
    }
    public Optional<Suser> findById(Long id){
        Optional<Suser> obj = repo.findById(id);
        return obj;
    }
    public Suser createSuser(String TCNumber, String password){
        Suser obj = new Suser(TCNumber, password);
        repo.save(obj);
        return obj;
    }

    public Optional<Suser> getUser(Long id){
        Optional<Suser> obj = repo.findById(id);
        return obj;
    }

//    public String createDisease(Long id, String disease){
//        crepo.save(new ChronicDisease(id, disease));
//        return "";
//    }
    public Symptom createSymptom(Long id, String symptom){
        Symptom newSymptom = Symptom.builder().suser_fk(id).symptom(symptom).count(1).build();
        //Symptom symptom1 = new Symptom(symptom);
        srepo.save(newSymptom);
        return newSymptom;
    }
    // select from symptom where symptom = "parameter" and suserid = parametre userid;



    public Optional<Symptom> findSymptomForUser(Long id, String symptomName){
        Optional<Symptom> symptom = srepo.findAll().stream().filter(s->s.getSymptom().equals(symptomName) && id.equals(s.getSuser_fk())).findFirst();

        return symptom;
    }

    public void updateSymptom(Symptom symptom, Long userId){

        srepo.save(symptom);

        if (symptom.getCount() % 3 == 0) {
            Alert newAlert = new Alert(symptom.getId(), userId, "");

            arepo.save(newAlert);
        }
    }

    public List<Alert> getAllAlerts(Long userId){
        List<Alert> alerts = arepo.findAllByuserId(userId);
        return alerts;
    }

    public void createAlert(Alert alert){
        arepo.save(alert);
    }
    public void deleteAlert(Long alertId){
        arepo.deleteById(alertId);
    }

    public Suser updateUser(InfoDTO request){
        Optional<Suser> s = findById(request.getUserId());
        s.get().setAge(request.getAge());
        s.get().setFullName(request.getFullName());
        s.get().setHeight(request.getHeight());
        s.get().setWeight(request.getWeight());
        s.get().setGender(request.getGender());
        repo.save(s.get());
        return s.get();
    }
}
