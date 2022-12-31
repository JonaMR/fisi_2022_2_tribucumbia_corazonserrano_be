package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Trimestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrimestreRepository extends JpaRepository<Trimestre, Integer> {
}