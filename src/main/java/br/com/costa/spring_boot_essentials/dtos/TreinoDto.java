package br.com.costa.spring_boot_essentials.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TreinoDto {

    @NotNull
    private Integer alunoId;
    @NotBlank
    private String nome;
    @NotBlank
    private List<Integer> exerciciosIds;

}
