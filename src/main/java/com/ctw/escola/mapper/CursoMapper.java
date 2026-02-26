package com.ctw.escola.mapper;

import com.ctw.escola.dto.curso.CursoRequestDto;
import com.ctw.escola.dto.curso.CursoResponseDto;
import com.ctw.escola.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso paraEntidade(
            CursoRequestDto cursoRequestDto
    ){
        return new Curso(
                cursoRequestDto.nome(),
                cursoRequestDto.codigo()
        );
    }

    public CursoResponseDto paraResponseDto(
            Curso curso
    ){
        return new CursoResponseDto(
                curso.getId(),
                curso.getNome(),
                curso.getCodigo()
        );
    }
}
