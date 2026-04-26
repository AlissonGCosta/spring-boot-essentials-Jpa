package br.com.costa.spring_boot_essentials.services;

import br.com.costa.spring_boot_essentials.database.model.AlunosEntity;
import br.com.costa.spring_boot_essentials.database.model.ExerciciosEntity;
import br.com.costa.spring_boot_essentials.database.model.TreinosEntity;
import br.com.costa.spring_boot_essentials.database.model.repository.IAlunosRepository;
import br.com.costa.spring_boot_essentials.database.model.repository.IExerciciosRepository;
import br.com.costa.spring_boot_essentials.database.model.repository.ITreinosRepository;
import br.com.costa.spring_boot_essentials.dtos.TreinoDto;
import br.com.costa.spring_boot_essentials.exception.BadRequestException;
import br.com.costa.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final IAlunosRepository alunosRepository;
    private final IExerciciosRepository exerciciosRepository;
    private final ITreinosRepository treinosRepository;


    public void criarTreino(TreinoDto treinoDto) throws NotFoundException, BadRequestException {

        Set<ExerciciosEntity> exercicios = new HashSet<>();

        AlunosEntity aluno = alunosRepository.findById(treinoDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        TreinosEntity treino = treinosRepository.findByNomeAndAlunoId(treinoDto.getNome(), treinoDto.getAlunoId())
                .orElse(null);

        if (treino != null) {
            throw new BadRequestException("Ja existe um treino com esse nome  para esse aluno");
        }

        for(Integer exercicioId : treinoDto.getExerciciosIds()) {
            ExerciciosEntity exercicio = exerciciosRepository.findById(exercicioId)
                    .orElseThrow(() -> new NotFoundException(String.format("Exercicio %s não encontrado" , exercicioId)));

            exercicios.add(exercicio);
        }

        treino = TreinosEntity.builder()
                .nome(treinoDto.getNome())
                .aluno(aluno)
                .treinosExercicios(exercicios)
                .build();

        treinosRepository.save(treino);
    }

}
