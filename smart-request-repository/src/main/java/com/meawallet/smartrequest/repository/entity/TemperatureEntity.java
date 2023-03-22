package com.meawallet.smartrequest.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "temperatures")
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "temperature")
    private BigDecimal temperature;
    @Column(name = "unit")
    private String unit;
    @Column(name = "current_hour")
    private LocalDateTime currentHour;
    @Column(name = "time_stamp")
    private LocalDate timeStamp;
}
