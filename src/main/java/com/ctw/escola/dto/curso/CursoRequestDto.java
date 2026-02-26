package com.ctw.escola.dto.curso;

import com.ctw.escola.model.Professor;

import java.util.List;

public record CursoRequestDto(
        String nome,
        String codigo,
        List<Professor> listaProfessorIds
) {
}
