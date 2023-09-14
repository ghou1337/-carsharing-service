package pl.hibernate.study.demo.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @ManyToOne
    @JoinColumn(name = "user_car", referencedColumnName = "user_id")
    @OrderBy("id")
    private User userVehicle;
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

    public Vehicle() {
    }

    public Vehicle(int id, String carBrand, int priceRent, int carYear) {
        this.id = id;
        this.carBrand = carBrand;
        this.priceRent = priceRent;
        this.carYear = carYear;
    }

    public User getUserVehicle() {
        return userVehicle;
    }

    public void setUserVehicle(User userVehicle) {
        this.userVehicle = userVehicle;
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
}
