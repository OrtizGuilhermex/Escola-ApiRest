package com.ctw.escola.dto.turma;

import com.ctw.escola.model.Aluno;

import java.util.List;

public record TurmaResponseDto (
        long id,
        String nome,
        String nome_curso,
        String nome_professor
) {
}
