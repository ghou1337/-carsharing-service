package pl.hibernate.study.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "rented_vehicles")
public class RentedVehicles {
    @Id
    private int id;

    @Column(name = "rent_start_at")
    private LocalDateTime rentStartAt;

    @Column(name = "rent_completion_at")
    private LocalDateTime rentCompletionAt;
}
