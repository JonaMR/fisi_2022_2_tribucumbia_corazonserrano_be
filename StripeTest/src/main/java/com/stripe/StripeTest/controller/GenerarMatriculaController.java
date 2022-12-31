package com.stripe.StripeTest.controller;

import com.stripe.StripeTest.Repository.AlumnoRepository;
import com.stripe.StripeTest.Repository.AulaRepository;
import com.stripe.StripeTest.Repository.GradoRepository;
import com.stripe.StripeTest.Repository.MatriculaRepository;
import com.stripe.StripeTest.Service.GradoService;
import com.stripe.StripeTest.model.Alumno;
import com.stripe.StripeTest.model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stripe.StripeTest.model.Grado;
import com.stripe.StripeTest.model.Aula;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/eduapp/generarMatricula")
public class GenerarMatriculaController {

    private final GradoService gradoService;
    private final GradoRepository gradoRepository;
    private final AulaRepository aulaRepository;
    private final AlumnoRepository alumnoRepository;

    private final MatriculaRepository matriculaRepository;

    @Autowired
    public GenerarMatriculaController(GradoService gradoService, GradoRepository gradoRepository,
                                      AulaRepository aulaRepository, AlumnoRepository alumnoRepository,
                                      MatriculaRepository matriculaRepository){
        this.gradoService = gradoService;
        this.gradoRepository = gradoRepository;
        this.aulaRepository = aulaRepository;
        this.alumnoRepository = alumnoRepository;
        this.matriculaRepository = matriculaRepository;

    }

    //Validacion no se matricule dos veces - QUEDA Getmapping
    @GetMapping(value ="/matriculadoPreviamente/{idAlumno}")
    public ResponseEntity<Boolean> validarReMatricula(@PathVariable Integer idAlumno){
        //Validamos en base a la tabla matricula
        Optional<Alumno> optAlumno = alumnoRepository.findById(idAlumno);
        Matricula matricula = matriculaRepository.findByIdAlumno(optAlumno.get());
        return ResponseEntity.ok(matricula == null);//Verdadero no se matriculo ninguna vez
        //Falso ya se matriculo y quiere hacerlo nuevamente - Caso errado
    }

    //Validacion vacantes disponibles para determinado grado
    @GetMapping(value ="/superaLimiteVacante/{idGrado}")
    public ResponseEntity<Boolean> validarVacantePorGrado(@PathVariable Integer idGrado){
        Boolean validacion = gradoService.superaLimiteMatriculadosPorGrado(idGrado);
        return ResponseEntity.ok(validacion);
        //Verdadero - Supera el limite de matriculados (caso errado)
        //Falso - No supera limite de matriculados (caso correcto)
    }

    //Lista de aulas previamente haber seleccionado grado
    @GetMapping(value ="/aulas/{idGrado}")
    @ResponseBody
    public ResponseEntity<List<Aula>> listaAulas(@PathVariable Integer idGrado){
        List<Aula> aulas = aulaRepository.findAllByGrado(idGrado);
        if(aulas == null || aulas.isEmpty()){
            return null;
        }else{
            return ResponseEntity.ok(aulas);
        }
    }

    //Asignar vacante - Actualizar tabla
    @PutMapping(value = "/asignarVacante/{idGrado}")
    public ResponseEntity<Grado> asignarVacante(@PathVariable Integer idGrado){
        Optional<Grado> grado1 = gradoRepository.findById(idGrado);
        if(grado1.isPresent()){
            grado1.get().setMatriculados(grado1.get().getMatriculados()+1);
            return ResponseEntity.ok(gradoRepository.save(grado1.get()));
        }else{
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping(value = "/generarMatriculaPendiente/{idAula}/{idAlumno}")
    public ResponseEntity<Matricula> guardarMatricula(@PathVariable Integer idAula, @PathVariable Integer idAlumno){
        Optional<Aula> aula1 = aulaRepository.findById(idAula);
        Optional<Alumno> alumno1 = alumnoRepository.findById(idAlumno);
        Matricula matricula = new Matricula();
        matricula.setFecha(LocalDate.now());
        matricula.setIdAlumno(alumno1.get());
        matricula.setIdAula(aula1.get());
        matricula.setAnioEscolar(2023);//Definido por defecto
        matricula.setEstado("PENDIENTE");
        return new ResponseEntity<>(matriculaRepository.save(matricula),HttpStatus.ACCEPTED);
    }

}
