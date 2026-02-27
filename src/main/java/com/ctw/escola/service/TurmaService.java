package com.ctw.escola.service;

import com.ctw.escola.dto.nota.NotaRequestDto;
import com.ctw.escola.dto.nota.NotaResponseDto;
import com.ctw.escola.dto.turma.TurmaRequestDto;
import com.ctw.escola.dto.turma.TurmaResponseDto;
import com.ctw.escola.mapper.TurmaMapper;
import com.ctw.escola.model.Nota;
import com.ctw.escola.model.Turma;
import com.ctw.escola.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;

    public TurmaService(TurmaRepository turmaRepository, TurmaMapper turmaMapper) {
        this.turmaRepository = turmaRepository;
        this.turmaMapper = turmaMapper;
    }

    public TurmaResponseDto postTurma(
            TurmaRequestDto turmaRequestDto) throws SQLException {

        Turma turma = turmaMapper.paraEntidade(turmaRequestDto);

        turmaRepository.postTurma(turma);

        return turmaMapper.paraResponseDto(turma);
    }

    public List<TurmaResponseDto> getNotas () throws SQLException{
        List<Turma> turmaList = turmaRepository.getTurmas();

        return turmaList.stream()
                .map(turmaMapper::paraResponseDto)
                .toList();
    }

    public TurmaResponseDto getTurmaPorID(long id) throws SQLException{

        Turma turma = turmaRepository.getTurmaPorId(id);

        return turmaMapper.paraResponseDto(turma);
    }

    public TurmaResponseDto putTurma(
            TurmaRequestDto turmaRequestDto, long id) throws SQLException{

        Turma turma = turmaMapper.paraEntidade(turmaRequestDto);

        turmaRepository.putTurma(turma);
        turma.setId(id);

        return turmaMapper.paraResponseDto(turma);
    }

    public void deleteTurma(long id) throws SQLException{
        turmaRepository.deletarTurma(id);
    }

}
