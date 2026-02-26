package com.ctw.escola.dto.aula;

import java.time.LocalDateTime;

public record AulaResponseDto (
        long id,
        long turma_id,
        LocalDateTime data_hora,
        String assunto
) {
}
