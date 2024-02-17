package pl.hibernate.study.demo.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
    private Float priceRent;
    @NotNull(message = "Car year shouldn't be empty")
    @Column(name = "car_year")
    private int carYear;
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "car_class")
    private String carClass;

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

    public Float getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(Float priceRent) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public List<RentedVehicleHistory> getRentedVehicles() {
        return rentedVehicleHistories;
    }

    public void setRentedVehicles(List<RentedVehicleHistory> rentedVehicleHistories) {
        this.rentedVehicleHistories = rentedVehicleHistories;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public VehicleImage getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(VehicleImage vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    @Override
    public String toString() {
        return carBrand + carModel + carClass + carYear ;
    }
}
