package com.ctw.escola.dto.professor;

public record ProfessorRequestDto(
        String nome,
        String email,
        String disciplina
) {
}
