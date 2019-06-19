package br.fatec.ra1713006.projeto.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DefaultService {

    public String addErrors(BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request){

        List<Map<String, String>> listaErros = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            listaErros.add(Collections.singletonMap("message", error.getDefaultMessage()));
        }

        redirectAttributes.addFlashAttribute("errors", listaErros);
        return "redirect:" + request.getHeader("Referer");

    }
}
