package com.ctw.escola.mapper;

import com.ctw.escola.dto.aula.AulaRequestDto;
import com.ctw.escola.dto.aula.AulaResponseDto;
import com.ctw.escola.model.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula paraEntidade(
            AulaRequestDto aulaRequestDtoDto
    ) {
        return new Aula(
                aulaRequestDtoDto.turma_id(),
                aulaRequestDtoDto.data_hora(),
                aulaRequestDtoDto.assunto()
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
