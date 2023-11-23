package pl.hibernate.study.demo.model;

import org.springframework.stereotype.Component;
import pl.hibernate.study.demo.model.enums.CarBodyType;

@Component
public class VehicleSearchFilter {
    private String minCarYear;

    private String maxCarYear;

    private String carYear;

    private String carBrand;

    private String minPriceRent;

    private String maxPriceRent;

    private String priceRent;

    private String carBodyType;

    public VehicleSearchFilter() {
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(String priceRent) {
        this.priceRent = priceRent;
    }


    public String getCarBodyType() {
        return carBodyType;
    }

    public void setCarBodyType(String carBodyType) {
        this.carBodyType = carBodyType;
    }

    public String getMinCarYear() {
        return minCarYear;
    }

    public void setMinCarYear(String minCarYear) {
        this.minCarYear = minCarYear;
    }

    public String getMaxCarYear() {
        return maxCarYear;
    }

    public void setMaxCarYear(String maxCarYear) {
        this.maxCarYear = maxCarYear;
    }

    public String getMinPriceRent() {
        return minPriceRent;
    }

    public void setMinPriceRent(String minPriceRent) {
        this.minPriceRent = minPriceRent;
    }

    public String getMaxPriceRent() {
        return maxPriceRent;
    }

    public void setMaxPriceRent(String maxPriceRent) {
        this.maxPriceRent = maxPriceRent;
    }

    public void setBasicValues() {
        this.carYear = "";
        this.carBrand = "";
        this.priceRent = "";
        this.carBodyType = "All";
    }

    public Boolean isEmpty() {
        this.carYear = "";
        this.carBrand = "";
        this.priceRent = "";
        this.carBodyType = "";
        return true;
    }

    @Override
    public String toString() {
        return "VehicleSearchFilter{" +
                "carYear='" + carYear + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", priceRent='" + priceRent + '\'' +
                ", selectedType='" + carBodyType + '\'' +
                '}';
    }
}
