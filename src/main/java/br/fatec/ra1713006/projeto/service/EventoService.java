package br.fatec.ra1713006.projeto.service;

import br.fatec.ra1713006.projeto.beans.EventoDTO;
import br.fatec.ra1713006.projeto.model.Evento;
import br.fatec.ra1713006.projeto.repository.EventoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;


    public Evento salvar(EventoDTO eventoDTO){
        Evento evento = new Evento();
        BeanUtils.copyProperties(eventoDTO, evento);
        //seta datetime
        evento.setDthrEvento(LocalDateTime.of(eventoDTO.getDtEvento(), eventoDTO.getHrEvento()));

        return this.eventoRepository.save(evento);
    }

    public List<Evento> listar(){
        return eventoRepository.findAll();
    }

    public Evento buscaPorId(Integer idEvento) {
        return eventoRepository.findById(idEvento).orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o eventos"));
    }
}
