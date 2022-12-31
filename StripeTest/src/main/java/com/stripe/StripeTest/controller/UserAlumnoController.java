package com.stripe.StripeTest.controller;


import com.stripe.StripeTest.Service.UserAlumnoService;
import com.stripe.StripeTest.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduapp/userStudent")
public class UserAlumnoController {

    private final UserAlumnoService userAlumnoService;

    @Autowired
    public UserAlumnoController(UserAlumnoService userAlumnoService) {
        this.userAlumnoService = userAlumnoService;
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> listAll(){return ResponseEntity.ok(userAlumnoService.listAll());}


    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable Integer id){
        return ResponseEntity.ok(userAlumnoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Alumno> saveCustomer(@RequestBody Alumno alumno){
        Alumno alumnoSaved = userAlumnoService.save(alumno);
        return new ResponseEntity<>(alumnoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateCustomer(@PathVariable Integer id, @RequestBody Alumno alumno){
        alumno.setId(id);
        alumno = userAlumnoService.update(alumno);
        return ResponseEntity.ok(alumno);
    }

}
