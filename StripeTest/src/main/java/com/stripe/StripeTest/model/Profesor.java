package com.stripe.StripeTest.model;

import javax.persistence.*;

@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 45)
    private String dni;

    @Column(name = "nombres", nullable = false, length = 45)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false, length = 45)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 45)
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento", nullable = false, length = 45)
    private String fechaNacimiento;

    @Column(name = "sexo", nullable = false, length = 45)
    private String sexo;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 45)
    private String telefono;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @Column(name = "curso", nullable = false, length = 45)
    private String curso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}