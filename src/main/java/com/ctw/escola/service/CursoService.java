package com.ctw.escola.service;

import com.ctw.escola.dto.curso.CursoRequestDto;
import com.ctw.escola.dto.curso.CursoResponseDto;
import com.ctw.escola.mapper.CursoMapper;
import com.ctw.escola.model.Curso;
import com.ctw.escola.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;


    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public CursoResponseDto postCurso(
            CursoRequestDto cursoRequestDto) throws SQLException {

        Curso curso = cursoMapper.paraEntidade(cursoRequestDto);

        cursoRepository.postCurso(curso);

        return cursoMapper.paraResponseDto(curso);
    }

    public List<CursoResponseDto> getCursos()throws SQLException{
        List<Curso> cursoList = cursoRepository.getCursos();

        return cursoList.stream()
                .map(cursoMapper::paraResponseDto)
                .toList();
    }

    public CursoResponseDto getCursoPorId(
                long id) throws SQLException{

        Curso curso = cursoRepository.getCursoPorId(id);

        return cursoMapper.paraResponseDto(curso);

    }

    public CursoResponseDto putCurso (
                CursoRequestDto cursoRequestDto, long id) throws SQLException {

        Curso curso = cursoMapper.paraEntidade(cursoRequestDto);

        cursoRepository.putCurso(curso);
        curso.setId(id);

        return cursoMapper.paraResponseDto(curso);

    }

    public void deleteCurso(long id) throws SQLException{
        cursoRepository.deletarAluno(id);
    }

}
