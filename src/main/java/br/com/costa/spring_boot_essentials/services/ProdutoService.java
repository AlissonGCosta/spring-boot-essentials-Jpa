package br.com.costa.spring_boot_essentials.services;

import br.com.costa.spring_boot_essentials.database.model.ProdutoModel;
import br.com.costa.spring_boot_essentials.dtos.ProdutoDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private static final List<ProdutoModel> PRODUTOS = new ArrayList<>();
    static {
                PRODUTOS.add(ProdutoModel.builder()
                        .id(1)
                        .nome("Notebooke")
                        .preco(new BigDecimal(6000))
                        .quantidade(19)
                        .build());

                PRODUTOS.add(ProdutoModel.builder()
                        .id(2)
                        .nome("Iphone")
                        .preco(new BigDecimal(7000))
                        .quantidade(10)
                        .build());

                PRODUTOS.add(ProdutoModel.builder()
                        .id(3)
                        .nome("mouse")
                        .preco(new BigDecimal(900))
                        .quantidade(15)
                        .build());
    }

    public List<ProdutoModel> findAll() {
        return new ArrayList<>(PRODUTOS);
    }

    public ProdutoModel createProduto(ProdutoDto produtoDto) {

        Integer identificador = PRODUTOS.stream()
                .mapToInt(ProdutoModel:: getId)
                .max()
                .orElse(0) + 1;


        ProdutoModel novoProduto = ProdutoModel.builder()
                .id(identificador)
                .nome(produtoDto.getNome())
                .preco(produtoDto.getPreco())
                .quantidade(produtoDto.getQuantidade())

        .build();

        PRODUTOS.add(novoProduto);

        return novoProduto;
    }

    public ProdutoModel updateProduto(ProdutoDto produtoDto, Integer id) {
       ProdutoModel produto = PRODUTOS.stream()
                .filter(p-> p.getId() == (id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

       produto.setNome(produtoDto.getNome());
       produto.setPreco(produtoDto.getPreco());
       produto.setQuantidade(produtoDto.getQuantidade());

        return  produto;
    }

    public void deleteProduto(Integer id) {
        PRODUTOS.removeIf(p -> p.getId() == id);
    }

}
