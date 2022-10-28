package com.procesos.negocio.jhon.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "el nombre no puede estar en blanco")
    private String nombre;
    @Column(length = 300, nullable = false)
    @NotBlank(message = "los apellidos no puede estar en blanco")
    private String apellidos;
    @NotBlank(message = "el documento no puede estar en blanco")
    @Column(length = 20, nullable = false)
    private String documento;
    @Column(length = 100)
    private String direccion;
    private Date fechaNacimiento;
    @Column(length = 15)
    private String telefono;
    @Column (unique = true, length = 100, nullable = false)
    @NotBlank(message = "el correo no puede estar en blanco")
    private String correo;
    @Column (length = 64, nullable = false)
    @NotBlank(message = "la contraseña no puede estar en blanco")
    private String password;
}