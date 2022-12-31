package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario, Integer> {
}