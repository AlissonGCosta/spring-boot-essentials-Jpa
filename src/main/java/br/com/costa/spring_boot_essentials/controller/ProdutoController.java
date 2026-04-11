package br.com.costa.spring_boot_essentials.controller;

import br.com.costa.spring_boot_essentials.database.model.ProdutoModel;
import br.com.costa.spring_boot_essentials.dtos.ProdutoDto;
import br.com.costa.spring_boot_essentials.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoModel> findAll(){

        return produtoService.findAll();

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel createProduto(@RequestBody ProdutoDto produtoDto) {
        return produtoService.createProduto(produtoDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel updateProduto(@PathVariable Integer id,
                                      @RequestBody ProdutoDto produtoDto) {
        return produtoService.updateProduto(produtoDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable Integer id) {
        produtoService.deleteProduto(id);
    }
}
