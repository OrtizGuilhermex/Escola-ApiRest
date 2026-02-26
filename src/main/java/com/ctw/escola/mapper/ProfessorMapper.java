package com.ctw.escola.mapper;

import com.ctw.escola.dto.professor.ProfessorRequestDto;
import com.ctw.escola.dto.professor.ProfessorResponseDto;
import com.ctw.escola.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor paraEntidade(
            ProfessorRequestDto professorRequestDto
    ){
        return new Professor(
                professorRequestDto.nome(),
                professorRequestDto.email(),
                professorRequestDto.disciplina()
        );
    }

    public ProfessorResponseDto paraResponseDto(
            Professor professor
    ){
        return new ProfessorResponseDto(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDisciplina()
        );
    }
}
