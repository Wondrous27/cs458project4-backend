package com.example.cs458project4.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(
        name = "disease"
)
public class ChronicDisease {
    @Id
    private Long id;
    private String disease;
}
