package com.ftms.ftmsapi.repository;
import com.ftms.ftmsapi.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    
}