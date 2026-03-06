package com.ctw.escola.dto.aula;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AulaRequestDto (
        @NotNull (message = "O ID da turma não pode ser nulo")
        long turma_id,
        LocalDateTime data_hora,
        @NotBlank (message = "O assunto não pode ser nulo")
        @Size(min = 1, max = 300, message = "O assunto pode conter no máximo 300 caracteres")
        String assunto
) {
}
