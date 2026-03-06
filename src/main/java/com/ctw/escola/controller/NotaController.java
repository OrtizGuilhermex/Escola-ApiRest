package com.ctw.escola.controller;


import com.ctw.escola.dto.nota.NotaRequestDto;
import com.ctw.escola.dto.nota.NotaResponseDto;
import com.ctw.escola.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public NotaResponseDto putNotas(
            @Valid @RequestBody NotaRequestDto notaRequestDto
    ){
        try {
            return notaService.postNota(notaRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<NotaResponseDto> getNotas(){
        try {
            return notaService.getNotas();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public NotaResponseDto getNotaPorId(
            @PathVariable long id
    ){
        try {
            return notaService.getNotasPorID(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public NotaResponseDto putNota(
            @Valid @RequestBody NotaRequestDto notaRequestDto,
            @PathVariable long id
    ){
        try {
            return notaService.putNotas(notaRequestDto,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteNota(
            @PathVariable long id
    ){
        try {
            notaService.deleteNota(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
