package com.stripe.StripeTest.model;

import java.util.Date;

public class Card {

    private Long number;

    private int cvc;

    private int mes_expiracion;

    private int anio_expiracion;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public int getMes_expiracion() {
        return mes_expiracion;
    }

    public void setMes_expiracion(int mes_expiracion) {
        this.mes_expiracion = mes_expiracion;
    }

    public int getAnio_expiracion() {
        return anio_expiracion;
    }

    public void setAnio_expiracion(int anio_expiracion) {
        this.anio_expiracion = anio_expiracion;
    }
}
