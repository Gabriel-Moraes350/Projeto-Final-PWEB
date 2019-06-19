package br.fatec.ra1713006.projeto.beans;

import br.fatec.ra1713006.projeto.model.Evento;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventoDTO {

    private Integer idEvento;

    @NotEmpty(message = "[Nome de eventos] não pode ser vazio")
    @Size(min = 5, max = 50, message = "[Nome de eventos] deve ter entre 5 e 50 caracteres")
    private String nomeEvento;

    @NotNull(message = "[Data de Evento] não pode ser vazia")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtEvento;


    @NotNull(message = "[Hora de Evento] não pode ser vazia")
    @DateTimeFormat(pattern = "hh:mm a")
    private LocalTime hrEvento;

    @NotEmpty(message = "[Local de eventos] não pode ser vazio")
    @Size(max = 100, message = "[Local de eventos] deve ter no máximo 100 caracteres")
    private String localEvento;

    public EventoDTO(Evento evento) {
        this.idEvento = evento.getIdEvento();
        this.nomeEvento = evento.getNomeEvento();
        this.localEvento = evento.getLocalEvento();
        this.dtEvento = evento.getDthrEvento().toLocalDate();
        this.hrEvento = evento.getDthrEvento().toLocalTime();
    }

    public EventoDTO() {
    }
}
