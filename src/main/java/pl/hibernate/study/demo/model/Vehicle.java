package pl.hibernate.study.demo.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Car brand shouldn't be empty")
    @Column(name = "car_brand")
    private String carBrand;
    @NotNull(message = "Price shouldn't be empty")
    @Column(name = "price_rent")
    private Float priceRent;
    @NotNull(message = "Car year shouldn't be empty")
    @Column(name = "car_year")
    private Integer carYear;
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "availability")
    private Boolean availability;

    @NotEmpty(message = "Car class shouldn't be empty")
    @Column(name = "car_class")
    private String carClass;

    @NotEmpty(message = "Car model shouldn't be empty")
    @Column(name = "car_model")
    private String carModel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rentedCar")
    private List<RentedVehicleHistory> rentedVehicleHistories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<RentingVehicle> rentingVehicles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private VehicleImage vehicleImage;

    public Vehicle() {
    }

    public Vehicle(int id, String carBrand, Float priceRent, int carYear, String imageUrl, Boolean availability, String carClass, String carModel) {
        this.id = id;
        this.carBrand = carBrand;
        this.priceRent = priceRent;
        this.carYear = carYear;
        this.imageUrl = imageUrl;
        this.availability = availability;
        this.carClass = carClass;
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return carBrand + carModel + carClass + carYear ;
    }
}
