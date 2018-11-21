package com.ftms.ftmsapi.repository;
import com.ftms.ftmsapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    List<Employee> findByIdIn(List<Long> userIds);

    Boolean existsByEmail(String email);
}

