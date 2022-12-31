package com.stripe.StripeTest.Repository;


import com.stripe.StripeTest.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Integer> {

    List<Aula> findAllByGrado(Integer grado);
}