package com.ctw.escola.mapper;

import com.ctw.escola.dto.turma.TurmaRequestDto;
import com.ctw.escola.dto.turma.TurmaResponseDto;
import com.ctw.escola.model.Curso;
import com.ctw.escola.model.Professor;
import com.ctw.escola.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public Turma paraEntidade(
            TurmaRequestDto turmaRequestDto
    ){
        Curso curso = new Curso();
        curso.setId(turmaRequestDto.curso_id());

        Professor professor = new Professor();
        professor.setId(turmaRequestDto.professor_id());

        return new Turma(
                turmaRequestDto.nome(),
                curso,
                professor
        );
    }

    public TurmaResponseDto paraResponseDto(
            Turma turma
    ){
        return new TurmaResponseDto(
                turma.getId(),
                turma.getNome(),
                turma.getCurso().getNome(),
                turma.getProfessor().getNome()
        );
    }

}
