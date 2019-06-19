package br.fatec.ra1713006.projeto.controller;

import br.fatec.ra1713006.projeto.beans.EventoDTO;
import br.fatec.ra1713006.projeto.model.Evento;
import br.fatec.ra1713006.projeto.service.DefaultService;
import br.fatec.ra1713006.projeto.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("eventos")
public class EventoController {
    @Autowired
    private EventoService service;

    @Autowired
    private DefaultService defaultService;

    @RequestMapping(value = "cadastrar", method = RequestMethod.GET)
    public String cadastrar(@RequestParam(name = "evento", required = false) Integer idEvento, Model model) {
        EventoDTO evento = new EventoDTO();
        if (idEvento != null) {

            evento = new EventoDTO(service.buscaPorId(idEvento));
        }

        model.addAttribute("evento", evento);
        model.addAttribute("title", "Evento");

        return "eventos/cadastrar";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String salvar(@Valid EventoDTO eventoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {


        if (bindingResult.hasErrors()) {
            return defaultService.addErrors(bindingResult, redirectAttributes, request);
        }

        Evento evento = service.salvar(eventoDTO);

        redirectAttributes.addFlashAttribute("message", "Registro salvo com sucesso!");
        return "redirect:/eventos/cadastrar?evento=" + evento.getIdEvento();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("title", "Lista de Eventos");

        List<EventoDTO> dtos = service.listar().stream().map(EventoDTO::new).collect(Collectors.toList());
        model.addAttribute("items", dtos);

        return "eventos/listar";
    }
}
