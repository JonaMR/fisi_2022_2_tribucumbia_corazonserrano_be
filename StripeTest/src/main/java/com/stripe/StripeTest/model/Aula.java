package com.stripe.StripeTest.model;

import javax.persistence.*;

@Entity
@Table(name = "aula")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula", nullable = false)
    private Integer id;

    @Column(name = "grado", nullable = false)
    private Integer grado;

    @Column(name = "seccion", nullable = false, length = 45)
    private String seccion;

    @Column(name = "vacantes", nullable = false)
    private Integer vacantes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public void setVacantes(Integer vacantes) {
        this.vacantes = vacantes;
    }

}