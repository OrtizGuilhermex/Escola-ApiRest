package com.ctw.escola.dto.nota;

import com.ctw.escola.model.Aluno;
import com.ctw.escola.model.Aula;

public record NotaResponseDto (
        long id,
        String alunoNome,
        String aulaAssunto,
        double valor
) {
}
