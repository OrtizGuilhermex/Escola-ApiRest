package com.ctw.escola.service;

import com.ctw.escola.dto.aluno.AlunoRequestDto;
import com.ctw.escola.dto.aluno.AlunoResponseDto;
import com.ctw.escola.mapper.AlunoMapper;
import com.ctw.escola.model.Aluno;
import com.ctw.escola.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    public AlunoResponseDto postAluno(
                AlunoRequestDto alunoRequestDto)throws SQLException{

        Aluno aluno = alunoMapper.paraEntidade(alunoRequestDto);

        alunoRepository.postAluno(aluno);

        return alunoMapper.paraResponseDto(aluno);
    }

    public List<AlunoResponseDto> getAlunos() throws SQLException{
        List<Aluno> alunosList = alunoRepository.getAlunos();

        return alunosList.stream()
                .map(alunoMapper::paraResponseDto)
                .toList();
    }

    public AlunoResponseDto getAlunosPorId(long id)throws SQLException{
        Aluno aluno = alunoRepository.getAlunosPorId(id);

        return alunoMapper.paraResponseDto(aluno);
    }

    public AlunoResponseDto putAluno (
            AlunoRequestDto alunoRequestDto, long id)throws SQLException{

        Aluno aluno = alunoMapper.paraEntidade(alunoRequestDto);

        alunoRepository.putAluno(aluno);
        aluno.setId(id);

        return alunoMapper.paraResponseDto(aluno);
    }

    public void deletarAluno (long id) throws SQLException{
        alunoRepository.deletarAluno(id);
    }
}
