package com.stripe.StripeTest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aula", nullable = false)
    private Aula idAula;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alumno", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Alumno idAlumno;

    @Column(name = "anio_escolar", nullable = false)
    private Integer anioEscolar;

    @Column(name = "estado", nullable = false, length = 45)
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Aula getIdAula() {
        return idAula;
    }

    public void setIdAula(Aula idAula) {
        this.idAula = idAula;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getAnioEscolar() {
        return anioEscolar;
    }

    public void setAnioEscolar(Integer anioEscolar) {
        this.anioEscolar = anioEscolar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}