package com.ctw.escola.dto.curso;

import com.ctw.escola.model.Professor;

import java.util.List;

public record CursoResponseDto (
        long id,
        String nome,
        String codigo
) {
}
