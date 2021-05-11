package com.example.cs458project4;

import com.example.cs458project4.Models.Suser;
import com.example.cs458project4.Repository.SuserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Cs458project4Application {

    public static void main(String[] args) {
        SpringApplication.run(Cs458project4Application.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(SuserRepository suserRepository){
//        return args ->{
//            Suser me = new Suser(
//                    "abc",
//                    "sdf"
//            );
//            suserRepository.save(me);
//            Suser s = suserRepository.findByTCNumber("abd");
//            System.out.println("BURAYA BAAAAK " +s);
//        };
//    }
}
