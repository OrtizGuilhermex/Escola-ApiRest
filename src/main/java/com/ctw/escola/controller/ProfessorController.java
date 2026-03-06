package com.ctw.escola.controller;

import com.ctw.escola.dto.professor.ProfessorRequestDto;
import com.ctw.escola.dto.professor.ProfessorResponseDto;
import com.ctw.escola.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private  final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorResponseDto postProfessor(
            @Valid @RequestBody ProfessorRequestDto professorRequestDto
    ){
        try {
            return professorService.postProfessor(professorRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<ProfessorResponseDto> getProfessores(){
        try {
            return professorService.getProfessores();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ProfessorResponseDto getProfessorPorId(long id){
        try{
            return professorService.getProfessorPorid(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ProfessorResponseDto putProfessor(
            @Valid @RequestBody ProfessorRequestDto professorRequestDto,
            @PathVariable long id
    ){
        try {
            return professorService.putProfessor(professorRequestDto,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProfessor(
            @PathVariable long id
    ){
        try {
            professorService.deleteProfessor(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
