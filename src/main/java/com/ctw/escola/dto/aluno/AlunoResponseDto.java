package com.ctw.escola.dto.aluno;

import java.time.LocalDate;

public record AlunoResponseDto (
        long id,
        String nome,
        String email,
        String matricula,
        LocalDate data_nascimento
) {
}
