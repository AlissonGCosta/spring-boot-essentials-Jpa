package br.com.costa.spring_boot_essentials.dtos;

import java.math.BigDecimal;

public interface AvaliacoesFisicaProjection {

    Integer getIdAluno();
    String getNomeAluno();
    Integer getIdAvliacao();
    BigDecimal getPeso();
    BigDecimal getAltura();
    BigDecimal getPercentualGorduraCorporal();

}
