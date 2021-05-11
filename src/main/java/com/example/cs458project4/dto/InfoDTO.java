package com.example.cs458project4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InfoDTO {
    private Long userId;
    private String fullName;
    private String age;
    private String gender;
    private String height;
    private String weight;
}
