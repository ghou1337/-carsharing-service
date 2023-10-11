package pl.hibernate.study.demo.model;

import org.springframework.stereotype.Component;

@Component
public class VehicleSearchFilter {
    private String carYear;

    private String carBrand;

    private String priceRent;

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

    @Override
    public String toString() {
        return "VehicleSearchFilter{" +
                "carYear=" + carYear +
                ", carBrand='" + carBrand + '\'' +
                ", priceRent=" + priceRent +
                '}';
    }
}
