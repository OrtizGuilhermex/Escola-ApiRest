package com.ctw.escola.dto.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestDto(
        @NotBlank (message = "O nome não pode ser branco")
        String nome,
        @NotBlank(message = "O E-mail não pode ser branco")
        @Email(message = "E-mail inválido")
        String email,
        @NotBlank(message = "A disciplina não deve ser nula")
        String disciplina
) {
}
