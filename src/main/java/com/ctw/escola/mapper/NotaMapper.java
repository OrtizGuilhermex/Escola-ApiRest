package com.ctw.escola.mapper;

import com.ctw.escola.dto.nota.NotaRequestDto;
import com.ctw.escola.dto.nota.NotaResponseDto;
import com.ctw.escola.model.Aluno;
import com.ctw.escola.model.Aula;
import com.ctw.escola.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public Nota paraEntidade (
            NotaRequestDto notaRequestDto
    ){
       Aluno aluno = new Aluno();
       aluno.setId(notaRequestDto.aluno_id());

       Aula aula = new Aula();
       aula.setId(notaRequestDto.aula_id());

       return new Nota(
               aluno,
               aula,
               notaRequestDto.valor()
       );
    }

    public NotaResponseDto paraResponseDto(
            Nota nota
    ){
        return new NotaResponseDto(
                nota.getId(),
                nota.getAluno().getNome(),
                nota.getAula().getAssunto(),
                nota.getValor()
        );
    }
}
