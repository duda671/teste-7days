package saude.api.api.controller;

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
        return "crud"; // Redireciona para a página principal de CRUD
    }

    @PostMapping("/salvar")
    public String salvarRefeicao(@ModelAttribute Refeicao refeicao) {
        refeicaoRepository.save(refeicao);
        return "redirect:/crud"; // Redireciona para o CRUD principal
    }

    @GetMapping("/editar/{id}")
    public String editarRefeicao(@PathVariable Long id, Model model) {
        Refeicao refeicao = refeicaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Refeição não encontrada: " + id));
        model.addAttribute("refeicao", refeicao);
        return "crud"; // Retorna para o CRUD onde o formulário de edição será exibido
    }

    @GetMapping("/excluir/{id}")
    public String excluirRefeicao(@PathVariable Long id) {
        refeicaoRepository.deleteById(id);
        return "redirect:/crud";
    }
}
