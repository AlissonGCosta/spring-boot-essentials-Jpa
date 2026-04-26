package br.com.costa.spring_boot_essentials.controller;


import br.com.costa.spring_boot_essentials.dtos.TreinoDto;
import br.com.costa.spring_boot_essentials.exception.BadRequestException;
import br.com.costa.spring_boot_essentials.services.TreinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/treinos")
@RequiredArgsConstructor
@Validated
public class TreinosController {

    private final TreinoService treinoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTreino(@Valid @RequestBody TreinoDto treinoDto) throws BadRequestException {
        treinoService.criarTreino(treinoDto);
    }
}
