package com.example.pmdm_04_creacionvistasporcodigo.modelos;

import java.io.Serializable;

public class AlumnoModel implements Serializable {

    private String nombre;
    private String appelidos;
    private String ciclo;
    private char grupo;

    public AlumnoModel(String nombre, String appelidos, String ciclo, char grupo) {
        this.nombre = nombre;
        this.appelidos = appelidos;
        this.ciclo = ciclo;
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAppelidos() {
        return appelidos;
    }

    public void setAppelidos(String appelidos) {
        this.appelidos = appelidos;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "AlumnoModel{" +
                "nombre='" + nombre + '\'' +
                ", appelidos='" + appelidos + '\'' +
                ", ciclo='" + ciclo + '\'' +
                ", grupo=" + grupo +
                '}';
    }
}
