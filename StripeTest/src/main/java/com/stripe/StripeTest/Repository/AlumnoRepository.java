package com.stripe.StripeTest.Repository;
import com.stripe.StripeTest.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}