package com.stripe.StripeTest.model;

import javax.persistence.*;

@Entity
@Table(name = "trimestre")
public class Trimestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trimestre", nullable = false)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}