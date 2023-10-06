package pl.hibernate.study.demo.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "rent_history")
public class RentedVehicleHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rent_start_at")
    private LocalDateTime rentStartAt;

    @Column(name = "rent_completion_at")
    private LocalDateTime rentCompletionAt;

    @Column(name = "rent_duration")
    private Long rentDuration;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User carRenter;

    @ManyToOne
    @JoinColumn(name = "rented_car_id", referencedColumnName = "id")
    private Vehicle rentedCar;

    @Column(name = "hash")
    private String hash;

    public RentedVehicleHistory() {

    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getRentStartAt() {
        return rentStartAt;
    }

    public void setRentStartAt(LocalDateTime rentStartAt) {
        this.rentStartAt = rentStartAt;
    }

    public LocalDateTime getRentCompletionAt() {
        return rentCompletionAt;
    }

    public void setRentCompletionAt(LocalDateTime rentCompletionAt) {
        this.rentCompletionAt = rentCompletionAt;
    }

    public User getCarRenter() {
        return carRenter;
    }

    public void setCarRenter(User carRenter) {
        this.carRenter = carRenter;
    }

    public Vehicle getRentedCar() {
        return rentedCar;
    }

    public void setRentedCar(Vehicle rentedCar) {
        this.rentedCar = rentedCar;
    }

    public Long getRentDuration() {
        return Duration.between(this.rentStartAt, this.rentCompletionAt).toMinutes();
    }

    public void setRentDuration(Long rentDuration) {
        this.rentDuration = rentDuration;
    }

}
