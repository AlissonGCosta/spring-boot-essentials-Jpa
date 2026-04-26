package br.com.costa.spring_boot_essentials.controller;

import br.com.costa.spring_boot_essentials.dtos.AvaliacaoFisicaDto;
import br.com.costa.spring_boot_essentials.dtos.AvaliacoesFisicaProjection;
import br.com.costa.spring_boot_essentials.exception.BadRequestException;
import br.com.costa.spring_boot_essentials.services.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
@Validated
public class AvaliacoesFisicasController {

    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criaAvaliacaoFisica(@Valid @RequestBody AvaliacaoFisicaDto avaliacaoFisicaDto) throws BadRequestException {
        avaliacaoFisicaService.criarAvaliacaoFisica(avaliacaoFisicaDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacoesFisicaProjection> getAllAvaliacoes(){
        return avaliacaoFisicaService.getAllAvaliacoes();
    }
}
