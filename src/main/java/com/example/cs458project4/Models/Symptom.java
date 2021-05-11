package com.example.cs458project4.Models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(
        name = "symptom"
)
@Entity
public class Symptom {
    @Id
    @GeneratedValue
    private Long id;
    private String symptom;
    private int count;
    private Long suser_fk;

    public Symptom(String symptom) {
        this.symptom = symptom;
    }
}
