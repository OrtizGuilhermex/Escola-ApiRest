package com.ctw.escola.service;

import com.ctw.escola.dto.aula.AulaRequestDto;
import com.ctw.escola.dto.aula.AulaResponseDto;
import com.ctw.escola.mapper.AulaMapper;
import com.ctw.escola.model.Aula;
import com.ctw.escola.repository.AulaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;


    public AulaService(AulaRepository aulaRepository, AulaMapper aulaMapper) {
        this.aulaRepository = aulaRepository;
        this.aulaMapper = aulaMapper;
    }

    public AulaResponseDto postAula(
            AulaRequestDto aulaRequestDto) throws SQLException{

        Aula aula = aulaMapper.paraEntidade(aulaRequestDto);

        aulaRepository.postAula(aula);

        return aulaMapper.paraResponseDto(aula);
    }

    public List<AulaResponseDto> getAula() throws SQLException{
        List<Aula> aulaList = aulaRepository.getAulas();

        return aulaList.stream()
                .map(aulaMapper::paraResponseDto)
                .toList();
    }

    public AulaResponseDto getAulaPorId(long id) throws SQLException{
        Aula aula = aulaRepository.getAulaPorId(id);

        return aulaMapper.paraResponseDto(aula);
    }

    public AulaResponseDto putAula(AulaRequestDto aulaRequestDto,long id) throws SQLException{
        Aula aula = aulaMapper.paraEntidade(aulaRequestDto);

        aulaRepository.putAluno(aula);
        aula.setId(id);

        return aulaMapper.paraResponseDto(aula);
    }

    public void deleteAula(long id) throws SQLException{
        aulaRepository.deletarAula(id);
    }
}
