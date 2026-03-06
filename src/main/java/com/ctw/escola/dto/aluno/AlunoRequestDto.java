package com.ctw.escola.dto.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AlunoRequestDto(
        @NotBlank (message = "O nome não pode ser branco!")
        String nome,
        @NotBlank(message = "O E-mail não pode ser branco")
        @Email (message = "E-mail inválido")
        String email,
        @Size (min = 8, max = 20, message = "A matricula deve conter no mínimo 8 caracteres e no máximo 20")
        String matricula,
        LocalDate data_nascimento
) {
}
