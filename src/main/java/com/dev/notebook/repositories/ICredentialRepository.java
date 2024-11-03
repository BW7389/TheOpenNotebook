package com.dev.notebook.repositories;

import com.dev.notebook.models.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICredentialRepository extends JpaRepository<Credential,Long> {
    Optional<Credential> getCredentialByUserId(Long id);
}
