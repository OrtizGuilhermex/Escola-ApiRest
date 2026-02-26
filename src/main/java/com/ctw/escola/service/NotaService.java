package com.ctw.escola.service;

import com.ctw.escola.dto.nota.NotaRequestDto;
import com.ctw.escola.dto.nota.NotaResponseDto;
import com.ctw.escola.mapper.NotaMapper;
import com.ctw.escola.model.Nota;
import com.ctw.escola.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;


    public NotaService(NotaRepository notaRepository, NotaMapper notaMapper) {
        this.notaRepository = notaRepository;
        this.notaMapper = notaMapper;
    }

    public NotaResponseDto postNota(
            NotaRequestDto notaRequestDto) throws SQLException {

        Nota nota = notaMapper.paraEntidade(notaRequestDto);

        notaRepository.postNota(nota);

        return notaMapper.paraResponseDto(nota);
    }

    public List<NotaResponseDto> getNotas () throws SQLException{
        List<Nota> notaList = notaRepository.getNotas();

        return notaList.stream()
                .map(notaMapper::paraResponseDto)
                .toList();
    }

    public NotaResponseDto getNotasPorID(long id) throws SQLException{

        Nota nota = notaRepository.getNotaPorId(id);

        return notaMapper.paraResponseDto(nota);
    }

    public NotaResponseDto putNotas(
            NotaRequestDto notaRequestDto, long id) throws SQLException{

        Nota nota = notaMapper.paraEntidade(notaRequestDto);

        notaRepository.putAluno(nota);
        nota.setId(id);

        return notaMapper.paraResponseDto(nota);
    }

    public void deleteNota(long id) throws SQLException{
        notaRepository.deletarNota(id);
    }
}
