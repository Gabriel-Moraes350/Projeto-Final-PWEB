package br.fatec.ra1713006.projeto.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Evento")
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEVENTO", nullable = false)
    private Integer idEvento;

    @Column(name = "NOMEEVENTO", length = 50, nullable = false)
    private String nomeEvento;

    @Column(name = "DTHREVENTO", nullable = false)
    private LocalDateTime dthrEvento;

    @Column(name = "LOCALEVENTO", length = 100, nullable = false)
    private String localEvento;
}
