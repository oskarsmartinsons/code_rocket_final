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
    private Double temperature;
    @Column(name = "unit")
    private String unit;
    @Column(name = "last_time_updated")
    private LocalDateTime lastTimeUpdated;
    @Column(name = "time_stamp")
    private LocalDate timeStamp;
}
