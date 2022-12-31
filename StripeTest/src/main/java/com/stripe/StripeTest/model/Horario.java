package com.stripe.StripeTest.model;

import javax.persistence.*;

@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario", nullable = false)
    private Integer id;

    @Column(name = "hora_inicio", nullable = false, length = 45)
    private String horaInicio;

    @Column(name = "hora_fin", nullable = false, length = 45)
    private String horaFin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_aula", nullable = false)
    private Aula idAula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso idCurso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Aula getIdAula() {
        return idAula;
    }

    public void setIdAula(Aula idAula) {
        this.idAula = idAula;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

}