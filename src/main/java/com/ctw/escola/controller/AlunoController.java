package com.ctw.escola.controller;

import com.ctw.escola.dto.aluno.AlunoRequestDto;
import com.ctw.escola.dto.aluno.AlunoResponseDto;
import com.ctw.escola.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoResponseDto postAluno(
            @RequestBody AlunoRequestDto alunoRequestDto
    ){
        try{
            return alunoService.postAluno(alunoRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<AlunoResponseDto> getAlunos(){
        try{
            return  alunoService.getAlunos();
        }catch (SQLException e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AlunoResponseDto getALunoPorId(
            @PathVariable long id
    ){
        try {
            return alunoService.getAlunosPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public AlunoResponseDto putAluno(
            @RequestBody AlunoRequestDto alunoRequestDto,
            @PathVariable long id
    ){
        try {
            return alunoService.putAluno(alunoRequestDto,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(
            @PathVariable long id
    ){
        try {
            alunoService.deletarAluno(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
