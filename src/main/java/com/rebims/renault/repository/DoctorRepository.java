package com.rebims.renault.repository;

import com.rebims.renault.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findDoctorByUsernameAndPassword(String username, String password);
}
