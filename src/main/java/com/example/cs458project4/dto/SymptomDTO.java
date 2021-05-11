package com.example.cs458project4.dto;

import com.example.cs458project4.Models.Symptom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SymptomDTO {
    private Long id;
    private String symptom;
}
