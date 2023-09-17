package pl.hibernate.study.demo.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private int priceRent;
    @NotNull(message = "Car year shouldn't be empty")
    @Column(name = "car_year")
    private int carYear;

    @Column(name = "availability")
    private Boolean availability;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rentedCar")
    private List<RentedVehicle> rentedVehicles;

    public Vehicle() {
    }
    public Vehicle(int id, String carBrand, int priceRent, int carYear) {
        this.id = id;
        this.carBrand = carBrand;
        this.priceRent = priceRent;
        this.carYear = carYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Integer getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(int priceRent) {
        this.priceRent = priceRent;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public List<RentedVehicle> getRentedVehicles() {
        return rentedVehicles;
    }

    public void setRentedVehicles(List<RentedVehicle> rentedVehicles) {
        this.rentedVehicles = rentedVehicles;
    }
}
