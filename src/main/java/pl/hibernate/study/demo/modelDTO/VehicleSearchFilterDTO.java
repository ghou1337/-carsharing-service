package pl.hibernate.study.demo.modelDTO;

import org.springframework.stereotype.Component;

@Component
public class VehicleSearchFilterDTO {
    private String carYear;

    private String carBrand;

    private String priceRent;

    public VehicleSearchFilterDTO() {
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
        return "VehicleSearchFilterDTO{" +
                "carYear=" + carYear +
                ", carBrand='" + carBrand + '\'' +
                ", priceRent=" + priceRent +
                '}';
    }
}
