package com.example.cs458project4.Repository;

import com.example.cs458project4.Models.ChronicDisease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChronicDiseaseRepository extends JpaRepository<ChronicDisease, Long> {
}
