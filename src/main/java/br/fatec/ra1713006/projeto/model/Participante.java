package br.fatec.ra1713006.projeto.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "PARTICIPANTE")
@Data
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPARTICIPANTE", nullable = false)
    private Integer id;

    @Column(name = "NOMEPARTICIPANTE", length = 50, nullable = false)
    @Size(max = 50, min =5, message = "[NOME] - DEVE POSSUIR TER ENTRE 5 E 50 CARACTERES")
    private String nome;

    @Column(name = "DTNASCPARTICIPANTE", nullable = false)
    @NotNull(message = "[DATA NASCIMENTO] - NÃO DEVE SER VAZIA")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtNascimento;

    @Column(name = "EMPRESAPARTICIPANTE", nullable = false)
    @NotEmpty(message = "[EMPRESA] - NÃO DEVE SER VAZIA")
    private String empresa;

    @Column(name = "EMAILPARTICIPANTE", nullable = false)
    @NotEmpty(message = "[EMAIL] - NÃO DEVE VAZIO")
    @Email(message = "[EMAIL] - DEVE SER UM EMAIL VÁLIDO")
    private String email;

    @Column(name = "CPFPARTICIPANTE", nullable = false)
    @CPF(message = "[CPF] - DEVE SER VÁLIDO")
    @NotEmpty(message = "[CPF] - NÃO DEVE SER VAZIO")
    private String cpf;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENTO_IDEVENTO", nullable = false)
    private Evento evento;

}
