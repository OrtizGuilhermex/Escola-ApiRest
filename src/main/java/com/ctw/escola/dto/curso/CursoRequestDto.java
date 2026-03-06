package com.ctw.escola.dto.curso;

import com.ctw.escola.model.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CursoRequestDto(
        @NotBlank(message = "O nome não pode ser branco")
        String nome,
        @NotBlank (message = "O código não pode ser nulo")
        @Size(min = 1, max = 20, message = "O código deve conter no mínimo 1 caracter e no máximo 20 caracteres")
        String codigo
) {
}
