package com.ctw.escola.dto.turma;

import com.ctw.escola.model.Aluno;

import java.util.List;

public record TurmaRequestDto (
        String nome,
        long curso_id,
        long professor_id
){
}
