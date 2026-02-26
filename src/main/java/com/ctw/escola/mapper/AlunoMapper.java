package com.ctw.escola.mapper;

import com.ctw.escola.dto.aluno.AlunoRequestDto;
import com.ctw.escola.dto.aluno.AlunoResponseDto;
import com.ctw.escola.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade(
            AlunoRequestDto alunoRequestDto
    ){
        return new Aluno(
                alunoRequestDto.nome(),
                alunoRequestDto.email(),
                alunoRequestDto.matricula(),
                alunoRequestDto.data_nascimento()
        );
    }

    public AlunoResponseDto paraResponseDto(
            Aluno aluno
    ){
        return new AlunoResponseDto(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getData_nascimento()
        );
    }

}
