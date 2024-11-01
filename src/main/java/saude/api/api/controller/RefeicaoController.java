package saude.api.api.controller;

import saude.api.api.model.Exercicio;
import saude.api.api.model.Refeicao;
import saude.api.api.repository.RefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/refeicao")
public class RefeicaoController {

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @GetMapping("/listar")
    public String listarRefeicoes(Model model) {
        model.addAttribute("refeicoes", refeicaoRepository.findAll());
        model.addAttribute("refeicao", new Refeicao());
        return "crud";
    }

    @PostMapping("/salvar")
    public String salvarRefeicao(@ModelAttribute Refeicao refeicao) {
        refeicaoRepository.save(refeicao);
        return "redirect:/crud";
    }

    @GetMapping("/editar/{id}")
    public String editarRefeicao(@PathVariable Long id, Model model) {
        Refeicao refeicao = refeicaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Refeição não encontrada: " + id));
        model.addAttribute("refeicao", refeicao);
        return "crud";
    }

    @GetMapping("/excluir/{id}")
    public String excluirRefeicao(@PathVariable Long id) {
        refeicaoRepository.deleteById(id);
        return "redirect:/crud";
    }
}
