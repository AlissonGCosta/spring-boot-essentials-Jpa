package br.com.costa.spring_boot_essentials.database.model.repository;


import br.com.costa.spring_boot_essentials.database.model.AlunosEntity;
import br.com.costa.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {

    Optional<AlunosEntity> findByEmail(String email);

    @Query(value = "SELECT a FROM AlunosEntity a JOIN FETCH a.avaliacoesFisicas")
    Optional<AlunosEntity> findByIdFetch(Integer alunoId);
}
