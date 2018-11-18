package com.ftms.ftmsapi.repository;
import com.ftms.ftmsapi.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

}
