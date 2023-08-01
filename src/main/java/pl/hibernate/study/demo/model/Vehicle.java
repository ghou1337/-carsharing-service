package pl.hibernate.study.demo.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

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
    private int ID;
    @NotEmpty(message = "Shouldn't be empty")
    @Column(name = "car_brand")
    private String CARBRAND;
    @Column(name = "price_rent")
    private int PRICE_RENT;
    @Column(name = "car_year")
    private int CAR_YEAR;

    public Vehicle() {
    }

    public Vehicle(int ID, String CAR_BRAND, int PRICE_RENT, int CAR_YEAR) {
        this.ID = ID;
        this.CARBRAND = CAR_BRAND;
        this.PRICE_RENT = PRICE_RENT;
        this.CAR_YEAR = CAR_YEAR;
    }

    public String getCARBRAND() {
        return CARBRAND;
    }

    public void setCAR_BRAND(String CAR_BRAND) {
        this.CARBRAND = CAR_BRAND;
    }

    public int getPRICE_RENT() {
        return PRICE_RENT;
    }

    public void setPRICE_RENT(int PRICE_RENT) {
        this.PRICE_RENT = PRICE_RENT;
    }

    public int getCAR_YEAR() {
        return CAR_YEAR;
    }

    public void setCAR_YEAR(int CAR_YEAR) {
        this.CAR_YEAR = CAR_YEAR;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUserVehicle() {
        return userVehicle;
    }

    public void setUserVehicle(User userVehicle) {
        this.userVehicle = userVehicle;
    }

}
