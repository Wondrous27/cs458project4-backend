package com.example.cs458project4.Repository;

import com.example.cs458project4.Models.Alert;
import com.example.cs458project4.Models.ChronicDisease;
import com.example.cs458project4.Models.Suser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllByuserId(Long userId);

}
