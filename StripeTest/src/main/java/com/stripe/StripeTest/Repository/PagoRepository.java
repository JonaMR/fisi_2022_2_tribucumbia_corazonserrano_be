package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}