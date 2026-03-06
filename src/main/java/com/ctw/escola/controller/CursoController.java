package com.ctw.escola.controller;

import com.ctw.escola.dto.curso.CursoRequestDto;
import com.ctw.escola.dto.curso.CursoResponseDto;
import com.ctw.escola.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;


    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public CursoResponseDto postCurso(
            @Valid @RequestBody CursoRequestDto cursoRequestDto
    ){
        try {
            return cursoService.postCurso(cursoRequestDto);
        }catch (SQLException e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<CursoResponseDto> getCursos(){
        try{
            return cursoService.getCursos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public CursoResponseDto getCursoPorId(
            @PathVariable long id
    ){
        try {
            return cursoService.getCursoPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public CursoResponseDto putCurso(
            @Valid @RequestBody CursoRequestDto cursoRequestDto,
            @PathVariable long id
    ){
        try {
            return cursoService.putCurso(cursoRequestDto,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCurso(
            @PathVariable long id
    ){
        try {
            cursoService.deleteCurso(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
