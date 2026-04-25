package br.com.costa.spring_boot_essentials.controller;

import br.com.costa.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.costa.spring_boot_essentials.dtos.AlunoDto;
import br.com.costa.spring_boot_essentials.exception.BadRequestException;
import br.com.costa.spring_boot_essentials.exception.NotFoundException;
import br.com.costa.spring_boot_essentials.services.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated

public class AlunosController {

    private final AlunoService alunoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAluno(@Valid @RequestBody AlunoDto alunoDto) throws BadRequestException {
        alunoService.criarAluno(alunoDto);
    }

    @GetMapping("/{alunoId}/avaliacao")
    public AvaliacoesFisicasEntity getAvaliacoes(@PathVariable Integer alunoId) throws NotFoundException {
        return alunoService.getAlunoAvaliacao(alunoId);
    }

}
