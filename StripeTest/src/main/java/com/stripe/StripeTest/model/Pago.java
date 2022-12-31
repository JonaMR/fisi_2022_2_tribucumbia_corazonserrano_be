package com.stripe.StripeTest.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_matricula", nullable = false)
    private Matricula idMatricula;

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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Matricula getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Matricula idMatricula) {
        this.idMatricula = idMatricula;
    }

}