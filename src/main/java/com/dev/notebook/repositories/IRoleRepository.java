package com.dev.notebook.repositories;

import com.dev.notebook.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findRoleByName(String name);

    boolean existsByCode(String code);
}
