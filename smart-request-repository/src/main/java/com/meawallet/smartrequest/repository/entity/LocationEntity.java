package com.meawallet.smartrequest.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "locations")
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "lat")
    private String latitude;
    @Column(name = "lon")
    private Integer longitude;
    @OneToOne
    @JoinColumn(name = "temp_id", referencedColumnName = "id")
    private TemperatureEntity temperature;
}
