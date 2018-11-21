package com.ftms.ftmsapi.repository;

import com.ftms.ftmsapi.model.Role;
import com.ftms.ftmsapi.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
