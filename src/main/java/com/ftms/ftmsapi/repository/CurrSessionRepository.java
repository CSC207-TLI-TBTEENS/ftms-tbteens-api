package com.ftms.ftmsapi.repository;
import com.ftms.ftmsapi.model.CurrSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrSessionRepository extends JpaRepository<CurrSession, Long> {

}
