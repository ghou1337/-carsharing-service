package pl.hibernate.study.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hibernate.study.demo.model.VehicleImage;

@Repository
public interface VehicleImageRepo extends JpaRepository<VehicleImage, Long> {
}
