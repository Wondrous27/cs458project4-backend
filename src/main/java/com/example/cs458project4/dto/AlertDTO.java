package com.example.cs458project4.dto;

import com.example.cs458project4.Models.Alert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AlertDTO {
    private Alert alert;
}
