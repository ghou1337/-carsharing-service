package pl.hibernate.study.demo.model;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;
import pl.hibernate.study.demo.model.enums.CarBodyType;

import javax.validation.constraints.Size;

public class VehicleSearchFilter {
    @Size(min = 1980, max = 2030, message = "wrong min year")
    private Integer minCarYear;

    @Size(min = 1980, max = 2030, message = "wrong max year")
    private Integer maxCarYear;

    private Integer minPriceRent;

    private Integer maxPriceRent;

    private String carBodyType;

    private String carBrand;
    public VehicleSearchFilter() {
    }

    public Integer getMinCarYear() {
        return minCarYear;
    }

    public void setMinCarYear(Integer minCarYear) {
        this.minCarYear = minCarYear;
    }

    public Integer getMaxCarYear() {
        return maxCarYear;
    }

    public void setMaxCarYear(Integer maxCarYear) {
        this.maxCarYear = maxCarYear;
    }

    public Integer getMinPriceRent() {
        return minPriceRent;
    }

    public void setMinPriceRent(Integer minPriceRent) {
        this.minPriceRent = minPriceRent;
    }

    public Integer getMaxPriceRent() {
        return maxPriceRent;
    }

    public void setMaxPriceRent(Integer maxPriceRent) {
        this.maxPriceRent = maxPriceRent;
    }

    public String getCarBodyType() {
        return carBodyType;
    }

    public void setCarBodyType(String carBodyType) {
        this.carBodyType = carBodyType;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
}
