package com.ctw.escola.controller;

import com.ctw.escola.dto.nota.NotaRequestDto;
import com.ctw.escola.dto.nota.NotaResponseDto;
import com.ctw.escola.dto.turma.TurmaRequestDto;
import com.ctw.escola.dto.turma.TurmaResponseDto;
import com.ctw.escola.service.NotaService;
import com.ctw.escola.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }


    @PostMapping
    public TurmaResponseDto putTurma(
            @Valid @RequestBody TurmaRequestDto turmaRequestDto
    ){
        try {
            return turmaService.postTurma(turmaRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<TurmaResponseDto> getTurmas(){
        try {
            return turmaService.getNotas();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public TurmaResponseDto getTurmaPorId(
            @PathVariable long id
    ){
        try {
            return turmaService.getTurmaPorID(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public TurmaResponseDto putTurma(
            @Valid @RequestBody TurmaRequestDto turmaRequestDto,
            @PathVariable long id
    ){
        try {
            return turmaService.putTurma(turmaRequestDto,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteNota(
            @PathVariable long id
    ){
        try {
            turmaService.deleteTurma(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
