package br.fatec.ra1713006.projeto.service;

import br.fatec.ra1713006.projeto.model.Participante;
import br.fatec.ra1713006.projeto.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository repository;

    @Autowired
    private EventoService eventoService;

    public List<Participante> listarParticipantesPorEvento(Integer idEvento){

        return repository.findByEvento(eventoService.buscaPorId(idEvento));
    }

    public Participante salvar(Participante participante, Integer idEvento){
        participante.setEvento(eventoService.buscaPorId(idEvento));
        return repository.save(participante);
    }

    public Participante buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado o Participante"));
    }

    public void delete(Integer id){
        repository.delete(repository.getOne(id));
    }
}
