package com.ctw.escola.dto.nota;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record NotaRequestDto (
        @NotNull (message = "O ID do aluno não pode ser nulo")
        long aluno_id,
        @NotNull (message = "O ID da aula não pode ser nulo")
        long aula_id,
        @NotNull
        @PositiveOrZero(message = "A nota não pode ser negativa")
        @Max(value = 10, message = "A nota máxima é 10")
        double valor
) {
}
