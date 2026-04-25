package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController{

    @Autowired
    private UsuarioService service;

    @GetMapping("/cadastro")
    public String cadastroForm(Model model){
        model.addAttribute("usuario", new UsuarioModel());
        return "cadastro-usuario";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute UsuarioModel usuario){
        service.salvar(usuario);
        return "redirect:/login";
    }
}
