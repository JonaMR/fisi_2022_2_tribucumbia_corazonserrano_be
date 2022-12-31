package com.stripe.StripeTest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 45)
    private String dni;

    @Column(name = "nombres", nullable = false, length = 45)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false, length = 45)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 45)
    private String apellidoMaterno;

    @Column(name = "telefono", nullable = false, length = 45)
    private String telefono;

    @Column(name = "direccion", nullable = false, length = 45)
    private String direccion;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "sexo", nullable = false, length = 15)
    private String sexo;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Column(name = "dni_apoderado", nullable = false, length = 45)
    private String dniApoderado;

    @Column(name = "nombre_apoderado", nullable = false, length = 45)
    private String nombreApoderado;

    @Column(name = "correo_apoderado", nullable = false, length = 45)
    private String correoApoderado;

    @Column(name = "customerid", nullable = false, length = 20)
    private String customerid;

    @Column(name = "is_Stripe_Customer", nullable = false)
    private Boolean isStripeCustomer = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setIdUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDniApoderado() {
        return dniApoderado;
    }

    public void setDniApoderado(String dniApoderado) {
        this.dniApoderado = dniApoderado;
    }

    public String getNombreApoderado() {
        return nombreApoderado;
    }

    public void setNombreApoderado(String nombreApoderado) {
        this.nombreApoderado = nombreApoderado;
    }

    public String getCorreoApoderado() {
        return correoApoderado;
    }

    public void setCorreoApoderado(String correoApoderado) {
        this.correoApoderado = correoApoderado;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public Boolean getIsStripeCustomer() {
        return isStripeCustomer;
    }

    public void setIsStripeCustomer(Boolean isStripeCustomer) {
        this.isStripeCustomer = isStripeCustomer;
    }

}