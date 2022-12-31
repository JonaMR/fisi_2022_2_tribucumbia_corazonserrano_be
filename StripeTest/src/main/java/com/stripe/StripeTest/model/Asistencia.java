package com.stripe.StripeTest.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "asistencia", nullable = false)
    private Byte asistencia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno idAlumno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

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

    public Byte getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Byte asistencia) {
        this.asistencia = asistencia;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

}