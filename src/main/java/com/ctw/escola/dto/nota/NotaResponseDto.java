package com.ctw.escola.dto.nota;

public record NotaResponseDto (
        long id,
        String alunoNome,
        String aulaAssunto,
        double valor
) {
}
