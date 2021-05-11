package com.example.cs458project4.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(
        name = "alert"
)
public class Alert {
    @Id
    @GeneratedValue
    private Long id;
    private Long symptomId;
    private Long userId;
    private String message;
}
