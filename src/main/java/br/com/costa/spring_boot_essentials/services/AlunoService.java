package br.com.costa.spring_boot_essentials.services;

import br.com.costa.spring_boot_essentials.database.model.AlunosEntity;
import br.com.costa.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.costa.spring_boot_essentials.database.model.repository.IAlunosRepository;
import br.com.costa.spring_boot_essentials.dtos.AlunoDto;
import br.com.costa.spring_boot_essentials.exception.BadRequestException;
import br.com.costa.spring_boot_essentials.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final IAlunosRepository alunosRepository;

    public void criarAluno(@Valid AlunoDto alunoDto) throws BadRequestException {
       AlunosEntity aluno =  alunosRepository.findByEmail(alunoDto.getEmail())
                .orElse(null);

       if (aluno != null) {
           throw new BadRequestException("Aluno Ja Cadastro com este email");
       }

       alunosRepository.save(AlunosEntity.builder()
               .nome(alunoDto.getNome())
               .email(alunoDto.getEmail())
               .build());
    }

    public AvaliacoesFisicasEntity getAlunoAvaliacao(Integer alunoId) throws NotFoundException {
        AlunosEntity aluno = alunosRepository.findByIdFetch(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));


        AvaliacoesFisicasEntity avaliacaoFisica = aluno.getAvaliacoesFisicas();
            if(avaliacaoFisica == null) {
                throw new NotFoundException("Avaliação fisica não encontrada para esse aluno");
            }

     return avaliacaoFisica;
    }

}
