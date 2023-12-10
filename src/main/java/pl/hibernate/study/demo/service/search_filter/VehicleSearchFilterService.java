package pl.hibernate.study.demo.service.search_filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleSearchFilter;
import pl.hibernate.study.demo.repos.VehicleRepo;
import pl.hibernate.study.demo.utils.StringUtils;

import java.util.List;

@Component
public class VehicleSearchFilterService {
    @Autowired
    private VehicleRepo vehicleRepo;

    public List<Vehicle> getAllCarsWithFilterBoundaries(VehicleSearchFilter filter) {
        return vehicleRepo.getAllCarsByFilterValues(
                isEmptyFilter(filter.getCarBrand()) ? null : StringUtils.capitalizeFirstLetter(filter.getCarBrand()),
                filterPositiveNumber(filter.getMinPriceRent()), filterPositiveNumber(filter.getMaxPriceRent()),
                filterPositiveNumber(filter.getMinCarYear()), filterPositiveNumber(filter.getMaxCarYear()),
                isEmptyFilter(filter.getCarBodyType()) ? null : filter.getCarBodyType()
        );
    }

    private boolean isEmptyFilter(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            String stringValue = (String) value;
            return stringValue.isEmpty() || "ALL".equals(stringValue);
        } else {
            return false;
        }
    }
    private Integer filterPositiveNumber(Integer value) {
        if(value == null) {
            return null;
        } else if (value <= 0) {
            return null;
        }
        return value;
    }
}
