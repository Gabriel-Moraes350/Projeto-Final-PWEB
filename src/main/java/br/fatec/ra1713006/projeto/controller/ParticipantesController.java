package br.fatec.ra1713006.projeto.controller;

import br.fatec.ra1713006.projeto.model.Participante;
import br.fatec.ra1713006.projeto.service.DefaultService;
import br.fatec.ra1713006.projeto.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("participantes")
public class ParticipantesController {

    @Autowired
    private ParticipanteService service;

    @Autowired
    private DefaultService defaultService;

    @GetMapping
    public String listarParticipantes(@RequestParam("evento") Integer idEvento, Model model){

        model.addAttribute("items", service.listarParticipantesPorEvento(idEvento));
        model.addAttribute("title", "Participantes");
        return "participantes/listar";
    }

    @GetMapping("cadastrar")
    public String cadastrarParticipante(@RequestParam(name = "evento") Integer evento,
                                        @RequestParam(value = "id", required = false) Integer id,
                                        Model model){
        Participante participante = new Participante();
        if(id != null){
            participante = service.buscarPorId(id);
        }
        model.addAttribute("item", participante);

        return "participantes/cadastrar";
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public String salvarParticipante(@Valid Participante participante, BindingResult result, HttpServletRequest request, RedirectAttributes attributes){
        if(result.hasFieldErrors()){
            return defaultService.addErrors(result, attributes, request);
        }

        Integer idEvento = Integer.valueOf(request.getParameter("evento"));
        participante = service.salvar(participante, idEvento);

        attributes.addFlashAttribute("message", "Registro salvo com sucesso!");
        return "redirect:/participantes/?evento=" + participante.getEvento().getIdEvento();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
