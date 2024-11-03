package com.dev.notebook.repositories;

import com.dev.notebook.models.Confirmation;
import com.dev.notebook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConfirmationRepository extends JpaRepository<Confirmation,Long> {
    Optional<Confirmation> findConfirmationByConfirmationKey(String confirmationKey);
    Optional<Confirmation> findConfirmationByUser(User user);
}
