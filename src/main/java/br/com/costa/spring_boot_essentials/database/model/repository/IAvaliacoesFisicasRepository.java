package br.com.costa.spring_boot_essentials.database.model.repository;


import br.com.costa.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.costa.spring_boot_essentials.dtos.AvaliacoesFisicaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;


public interface IAvaliacoesFisicasRepository extends JpaRepository<AvaliacoesFisicasEntity, Integer> {

    @NativeQuery(value = """
        SELECT a.id                             idAluno,
               a.nome                           nomeAluno,
               af.id                            idAvaliacao, 
               af.peso                          peso,
               af.altura                        altura,
               af.percentual_gordura_corporal   percentualGorduraCorporal
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
        ON a.avaliacao_fisica_id = af.id
        """)
    List<AvaliacoesFisicaProjection> getAllAvaliacoes();


    @NativeQuery(value = """
        SELECT a.id                             idAluno,
               a.nome                           nomeAluno,
               af.id                            idAvaliacao, 
               af.peso                          peso,
               af.altura                        altura,
               af.percentual_gordura_corporal   percentualGorduraCorporal
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
        ON a.avaliacao_fisica_id = af.id
        """,
    countQuery = """
        SELECT count(af.id)
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
        ON a.avaliacao_fisica_id = af.id
        """)
    Page<AvaliacoesFisicaProjection> getAllAvaliacoesPage(Pageable pageable);
}
