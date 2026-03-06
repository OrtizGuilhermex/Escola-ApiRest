package com.ctw.escola.dto.turma;

import com.ctw.escola.model.Aluno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TurmaRequestDto (
        @NotBlank(message = "O nome da turma não pode ser branca")
        String nome,
        @NotNull(message = "O ID do curso não pode ser nulo")
        long curso_id,
        @NotNull (message = "O ID do professor não pode ser nulo")
        long professor_id
){
}
