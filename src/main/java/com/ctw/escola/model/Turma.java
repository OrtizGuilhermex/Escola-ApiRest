package com.ctw.escola.model;

public class Turma {

    private long id;
    private String nome;
    private Curso curso;
    private Professor professor;

    public Turma(long id, String nome, Curso curso, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.professor = professor;
    }

    public Turma(String nome, Curso curso, Professor professor) {
        this.nome = nome;
        this.curso = curso;
        this.professor = professor;
    }

    public Turma() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
