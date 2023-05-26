package pl.messages.web.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Vehicle {
    private int ID;
    private String CAR_BRAND;
    private int PRICE_RENT;
    private int CAR_YEAR;

    public Vehicle() {
    }

    public Vehicle(int ID, String CAR_BRAND, int PRICE_RENT, int CAR_YEAR) {
        this.ID = ID;
        this.CAR_BRAND = CAR_BRAND;
        this.PRICE_RENT = PRICE_RENT;
        this.CAR_YEAR = CAR_YEAR;
    }

    public String getCAR_BRAND() {
        return CAR_BRAND;
    }

    public void setCAR_BRAND(String CAR_BRAND) {
        this.CAR_BRAND = CAR_BRAND;
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
}