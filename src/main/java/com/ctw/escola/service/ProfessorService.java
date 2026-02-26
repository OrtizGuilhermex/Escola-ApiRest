package com.ctw.escola.service;

import com.ctw.escola.dto.professor.ProfessorRequestDto;
import com.ctw.escola.dto.professor.ProfessorResponseDto;
import com.ctw.escola.mapper.ProfessorMapper;
import com.ctw.escola.model.Professor;
import com.ctw.escola.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorResponseDto postProfessor(
            ProfessorRequestDto professorRequestDto)throws SQLException {

        Professor professor = professorMapper.paraEntidade(professorRequestDto);

        professorRepository.postProfessor(professor);

        return professorMapper.paraResponseDto(professor);
    }

    public List<ProfessorResponseDto> getProfessores() throws SQLException{
        List<Professor> professorList = professorRepository.getProfessores();

        return professorList.stream()
                .map(professorMapper::paraResponseDto)
                .toList();
    }

    public ProfessorResponseDto getProfessorPorid(long id) throws SQLException{

        Professor professor = professorRepository.getProfessorPorId(id);

        return professorMapper.paraResponseDto(professor);
    }

    public ProfessorResponseDto putProfessor(
            ProfessorRequestDto professorRequestDto, long id) throws SQLException{

        Professor professor = professorMapper.paraEntidade(professorRequestDto);
        professor.setId(id);

        professorRepository.putProfessor(professor);

        return professorMapper.paraResponseDto(professor);

    }

    public void deleteProfessor(long id) throws SQLException{
        professorRepository.deletarAluno(id);
    }

}
