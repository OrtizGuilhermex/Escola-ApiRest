package com.ctw.escola.controller;

import com.ctw.escola.dto.aula.AulaRequestDto;
import com.ctw.escola.dto.aula.AulaResponseDto;
import com.ctw.escola.model.Aula;
import com.ctw.escola.service.AulaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {


    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }


    @PostMapping
    public AulaResponseDto postAluno(
            @RequestBody AulaRequestDto aulaRequestDto
    ){
        try {
            return aulaService.postAula(aulaRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<AulaResponseDto> getAulas(){
        try {
            return aulaService.getAula();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AulaResponseDto getAulaPorId(
          @PathVariable long id
    ){
        try {
            return aulaService.getAulaPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping
    public AulaResponseDto putAula(
            @RequestBody AulaRequestDto aulaRequestDto,
            @PathVariable long id
    ){
        try {
            return aulaService.putAula(aulaRequestDto,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAula(
            @PathVariable long id
    ){
        try {
            aulaService.deleteAula(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
