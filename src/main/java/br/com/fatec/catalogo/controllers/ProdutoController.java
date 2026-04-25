package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.services.CategoriaService;
import br.com.fatec.catalogo.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService service;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/produtos")
    public String listarProdutos(@RequestParam(required = false) String nome, Model model) {

        List<ProdutoModel> produtos;

        if (nome != null && !nome.trim().isEmpty()) {
            produtos = service.buscarPorNome(nome);
        } else {
            produtos = service.listarTodos();
        }

        model.addAttribute("produtos", produtos);
        model.addAttribute("nome", nome);
        return "lista-produtos";
    }

    @GetMapping("/produtos/novo")
    public String exibirFormulario(Model model) {
        ProdutoModel produto = new ProdutoModel();
        produto.setCategoria(new CategoriaModel());
        List<CategoriaModel> categorias = categoriaService.listarTodas();
        if (categorias.isEmpty()) {
            return "redirect:/categorias/nova";
        }
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "cadastro-produto";
    }

    @PostMapping("/produtos/novo")
    public String salvarProduto(@Valid @ModelAttribute("produto") ProdutoModel produto, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro-produto";
        }

        try {
            service.salvar(produto);
            return "redirect:/produtos";
        } catch (RuntimeException e) {
            result.rejectValue("nome", "erro.nome", e.getMessage());
            return "cadastro-produto";
        }
    }

    @GetMapping("/produtos/editar/{id}")
    public String exibirEdicao(@PathVariable("id") long id, Model model) {
        ProdutoModel produto = service.buscarPorId(id);
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "editar-produto";
    }

    @PostMapping("/produtos/editar/{id}")
    public String atualizarProduto(@PathVariable("id") long id,
                                   @Valid @ModelAttribute("produto") ProdutoModel produto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            produto.setIdProduto(id);
            model.addAttribute("produto", produto);
            return "editar-produto";
        }

        try {
            produto.setIdProduto(id);
            service.salvar(produto);
            return "redirect:/produtos";
        } catch (RuntimeException e) {
            result.rejectValue("nome", "erro.nome", e.getMessage());
            return "editar-produto";
        }
    }

    @GetMapping("/produtos/excluir/{id}")
    public String excluirProduto(@PathVariable("id") long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }
}