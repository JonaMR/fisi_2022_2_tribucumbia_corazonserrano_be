package com.stripe.StripeTest.Service;

import com.stripe.StripeTest.model.Alumno;
import com.stripe.StripeTest.model.Matricula;

import java.util.List;

public interface UserAlumnoService {

    List<Alumno> listAll();

    Alumno getById(Integer id);

    Alumno save(Alumno alumno);

    Alumno update(Alumno alumno);

    Matricula update(Matricula matricula);


}
