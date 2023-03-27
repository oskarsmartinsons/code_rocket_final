package com.meawallet.smartrequest.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
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
    @Column(name = "temperature_at")
    private LocalDateTime temperatureAt;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @PrePersist
    public void prePersist() {
        expirationDate = LocalDateTime.now().plusHours(1);
        createdAt = LocalDateTime.now();
    }
}
