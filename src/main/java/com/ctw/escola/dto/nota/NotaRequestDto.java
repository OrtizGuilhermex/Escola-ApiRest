package com.ctw.escola.dto.nota;

public record NotaRequestDto (
        long aluno_id,
        long aula_id,
        double valor
) {
}
