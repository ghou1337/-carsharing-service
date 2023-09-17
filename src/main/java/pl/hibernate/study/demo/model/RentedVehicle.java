package pl.hibernate.study.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rented_vehicles")
public class RentedVehicle {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rent_start_at")
    private LocalDateTime rentStartAt;

    @Column(name = "rent_completion_at")
    private LocalDateTime rentCompletionAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User carRenter;

    @ManyToOne
    @JoinColumn(name = "rented_car_id", referencedColumnName = "id")
    private Vehicle rentedCar;

    public RentedVehicle() {

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
}
