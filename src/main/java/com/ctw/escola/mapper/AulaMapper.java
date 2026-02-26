package com.ctw.escola.mapper;

import com.ctw.escola.dto.aula.AulaResponseDto;
import com.ctw.escola.model.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula paraEntidade(
            AulaResponseDto aulaResponseDto
    ) {
        return new Aula(
                aulaResponseDto.turma_id(),
                aulaResponseDto.data_hora(),
                aulaResponseDto.assunto()
        );
    }

    public AulaResponseDto paraResponseDto(
            Aula aula
    ){
        return new AulaResponseDto(
                aula.getId(),
                aula.getTurma_id(),
                aula.getData_hora(),
                aula.getAssunto()
        );
    }
}
