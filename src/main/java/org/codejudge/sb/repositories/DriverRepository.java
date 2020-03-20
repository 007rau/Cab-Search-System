package org.codejudge.sb.repositories;

import org.codejudge.sb.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Driver findById(int id);
    Driver findByEmail(String email);
    Driver findByPhoneNumber(String phoneNumber);
    Driver findByCarNumber(String carNumber);
    Driver findByLicenseNumber (String licenseNumber);
}
