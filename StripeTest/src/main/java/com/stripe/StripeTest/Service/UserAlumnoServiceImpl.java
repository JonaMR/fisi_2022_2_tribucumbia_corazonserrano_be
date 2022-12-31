package com.stripe.StripeTest.Service;

import com.stripe.StripeTest.Repository.AlumnoRepository;
import com.stripe.StripeTest.Repository.MatriculaRepository;
import com.stripe.StripeTest.model.Alumno;
import com.stripe.StripeTest.model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserAlumnoServiceImpl implements UserAlumnoService{

    private final AlumnoRepository alumnoRepository;

    private final MatriculaRepository matriculaRepository;

    @Autowired
    public UserAlumnoServiceImpl(AlumnoRepository alumnoRepository,
                                 MatriculaRepository matriculaRepository){
        this.alumnoRepository = alumnoRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public List<Alumno> listAll(){
        return alumnoRepository.findAll();
    }

    public Alumno getById(Integer id){
        return alumnoRepository.findById(id).orElseThrow(()->new NoSuchElementException("No existe alumno con id: "+ id));
    }

    public Alumno save(Alumno alumno){
        boolean exists = alumno.getId() != null && alumnoRepository.existsById(alumno.getId());
        if(exists){
            throw new IllegalArgumentException("Alumno con id: "+ alumno.getId() + " ya existe");
        }
        return alumnoRepository.save(alumno);
    }

    public Alumno update(Alumno alumno){
        boolean exists = alumnoRepository.existsById(alumno.getId());
        if(!exists){
            throw new NoSuchElementException("Cliente con id: "+ alumno.getId() +" no existe");
        }
        return  alumnoRepository.save(alumno);
    }

    public Matricula update(Matricula matricula){
        boolean exists = matriculaRepository.existsById(matricula.getId());
        if(!exists){
            throw new NoSuchElementException("Cliente con id: "+ matricula.getId() +" no existe");
        }
        return  matriculaRepository.save(matricula);
    }


}
