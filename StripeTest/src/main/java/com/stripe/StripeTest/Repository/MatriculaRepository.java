package com.stripe.StripeTest.Repository;

import com.stripe.StripeTest.model.Alumno;
import com.stripe.StripeTest.model.Aula;
import com.stripe.StripeTest.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

    Matricula findByIdAlumno(Alumno idAlumno);
}