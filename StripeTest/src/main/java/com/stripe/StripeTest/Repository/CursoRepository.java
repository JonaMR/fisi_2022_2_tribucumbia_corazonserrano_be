package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}