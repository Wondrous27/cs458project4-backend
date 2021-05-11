package com.example.cs458project4.Repository;

import com.example.cs458project4.Models.Suser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuserRepository extends JpaRepository<Suser, Long> {
    Suser findByTCNumber(String TCNumber);
}
