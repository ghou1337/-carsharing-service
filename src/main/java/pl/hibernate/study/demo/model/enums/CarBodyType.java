package pl.hibernate.study.demo.model.enums;

public enum CarBodyType {
    SEDAN("Sedan"),
    SUV("Suv"),
    COUPE("Coupe"),
    HATCHBACK("Hatchback"),
    COMPACT("Compact"),
    OTHER("Other"),
    ALL("All");

    private final String carBodyTypeValue;
    CarBodyType(String carBodyTypeValue) {
        this.carBodyTypeValue = carBodyTypeValue;
    }

    public String getCarBodyTypeValue() {
        return carBodyTypeValue;
    }
}
