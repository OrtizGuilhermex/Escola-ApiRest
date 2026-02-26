package com.ctw.escola.dto.professor;

public record ProfessorResponseDto (
        long id,
        String nome,
        String email,
        String disciplina
) {
}
