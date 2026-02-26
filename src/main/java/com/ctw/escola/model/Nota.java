package com.ctw.escola.model;

public class Nota {

    private long id;
    private Aluno aluno;
    private Aula aula;
    private double valor;

    public Nota(long id, Aluno aluno, Aula aula, double valor) {
        this.id = id;
        this.aluno = aluno;
        this.aula = aula;
        this.valor = valor;
    }

    public Nota(Aluno aluno, Aula aula, double valor) {
        this.aluno = aluno;
        this.aula = aula;
        this.valor = valor;
    }

    public Nota() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
