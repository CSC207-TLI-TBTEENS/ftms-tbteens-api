package com.ftms.ftmsapi.repository;
import com.ftms.ftmsapi.model.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
    Optional<ClientUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}
