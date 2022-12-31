package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
}