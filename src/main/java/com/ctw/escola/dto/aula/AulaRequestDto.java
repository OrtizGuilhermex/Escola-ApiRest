package com.ctw.escola.dto.aula;

import java.time.LocalDateTime;

public record AulaRequestDto (
        long turma_id,
        LocalDateTime data_hora,
        String assunto
) {
}
