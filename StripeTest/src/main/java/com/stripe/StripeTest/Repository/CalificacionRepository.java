package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
}