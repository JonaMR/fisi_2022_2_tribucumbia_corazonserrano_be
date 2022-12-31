package com.stripe.StripeTest.model;

import javax.persistence.*;

@Entity
@Table(name = "grado")
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado", nullable = false)
    private Integer id;
    @Column(name = "limite", nullable = false)
    private Integer limite;

    @Column(name = "matriculados", nullable = false)
    private Integer matriculados;

    @Column(name = "precio", nullable = false)
    private Double precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public Integer getMatriculados() {
        return matriculados;
    }

    public void setMatriculados(Integer matriculados) {
        this.matriculados = matriculados;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}