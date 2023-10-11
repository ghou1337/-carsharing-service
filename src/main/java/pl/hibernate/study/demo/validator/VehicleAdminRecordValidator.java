package pl.hibernate.study.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.hibernate.study.demo.model.Vehicle;

@Component
public class VehicleAdminRecordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Vehicle.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Vehicle vehicle = (Vehicle) target;
        if (vehicle.getCarBrand() == null || vehicle.getCarBrand().isEmpty()) {
            errors.rejectValue("carBrand", "carBrand.required", "Car brand is required.");
        }
        if (vehicle.getCarYear() == null) {
            errors.rejectValue("carYear", "carYear.required", "Car year is required and");
        }
        if (vehicle.getPriceRent() == null) {
            errors.rejectValue("priceRent", "priceRent.required", "Car price is required.");
        }
    }
}
