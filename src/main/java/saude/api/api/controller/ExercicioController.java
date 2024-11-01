package saude.api.api.controller;

import saude.api.api.model.Exercicio;
import saude.api.api.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @GetMapping("/listar")
    public String listarExercicios(Model model) {
        model.addAttribute("exercicios", exercicioRepository.findAll());
        return "crud"; // Redireciona para a página principal de CRUD
    }

    @PostMapping("/salvar")
    public String salvarExercicio(@ModelAttribute Exercicio exercicio) {
        exercicioRepository.save(exercicio);
        return "redirect:/crud"; // Redireciona para o CRUD principal
    }

    @GetMapping("/editar/{id}")
    public String editarExercicio(@PathVariable Long id, Model model) {
        Exercicio exercicio = exercicioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exercício não encontrado: " + id));
        model.addAttribute("exercicio", exercicio);
        return "crud"; // Retorna para o CRUD onde o formulário de edição será exibido
    }

    @GetMapping("/excluir/{id}")
    public String excluirExercicio(@PathVariable Long id) {
        exercicioRepository.deleteById(id);
        return "redirect:/crud";
    }
}
