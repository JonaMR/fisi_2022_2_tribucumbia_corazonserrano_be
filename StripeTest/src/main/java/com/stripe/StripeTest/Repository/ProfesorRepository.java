package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}