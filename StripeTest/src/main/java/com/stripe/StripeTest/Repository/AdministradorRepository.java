package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}