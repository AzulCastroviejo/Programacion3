package org.example;

public class Alumno {
    private String nombre;
    private double nota;
    private String curso;

    public Alumno(String nombre) {
        this.nombre = nombre;
    }

    public Alumno() {
    }

    public Alumno(String nombre, double nota, String curso) {
        this.nombre = nombre;
        this.nota = nota;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre='" + nombre + '\'' + ", nota=" + nota + ", curso='" + curso + '\'' + '}';
    }
}
