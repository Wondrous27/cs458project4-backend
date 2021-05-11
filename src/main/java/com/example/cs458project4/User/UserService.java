package com.example.cs458project4.User;

import com.example.cs458project4.Models.Suser;
import com.example.cs458project4.Repository.ChronicDiseaseRepository;
import com.example.cs458project4.Repository.SuserRepository;
import com.example.cs458project4.Repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

    public UserService(SuserRepository repo, ChronicDiseaseRepository crepo, SymptomRepository srepo) {
        this.repo = repo;
        this.crepo = crepo;
        this.srepo = srepo;
    }

    public Suser findByTCNumber(String TCNumber){
        Suser obj = repo.findByTCNumber(TCNumber);
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

}
