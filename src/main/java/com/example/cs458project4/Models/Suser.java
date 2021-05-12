package com.example.cs458project4.Models;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
@Table(
        name = "suser",
        uniqueConstraints = {
                @UniqueConstraint(name = "tcnumber_unique", columnNames = "tcnumber")
        }
)
public class Suser {
    @Id
//    @SequenceGenerator(
//            name = "suser_sequence",
//            sequenceName = "suser_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = SEQUENCE,
//            generator = "suser_sequence"
//    )
//    @Column(
//            name = "id",
//            updatable = false,
//            nullable = false
//    )
    @GeneratedValue
    private Long id;
    @Column(
            name = "tcnumber",
            columnDefinition = "TEXT"
    )
    private String TCNumber;
    private String password;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String age;
    private String height;
    private String weight;
    @OneToMany(targetEntity = ChronicDisease.class, cascade = CascadeType.ALL)
    @JoinColumn(name="suser_fk", referencedColumnName = "id")
    private List<ChronicDisease> chronicDiseases;
    @OneToMany(targetEntity = Symptom.class, cascade = CascadeType.ALL)
    @JoinColumn(name="suser_fk", referencedColumnName = "id")
    private List<Symptom> symptoms;



    public Suser(String TCNumber, String password) {
        this.TCNumber = TCNumber;
        this.password = password;
    }


}
