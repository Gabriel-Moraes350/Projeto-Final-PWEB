package br.fatec.ra1713006.projeto.repository;

import br.fatec.ra1713006.projeto.model.Evento;
import br.fatec.ra1713006.projeto.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {

    List<Participante> findByEvento(Evento evento);
}
