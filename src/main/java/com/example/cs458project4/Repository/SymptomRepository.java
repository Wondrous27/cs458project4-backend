package com.example.cs458project4.Repository;

import com.example.cs458project4.Models.Suser;
import com.example.cs458project4.Models.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {

}
