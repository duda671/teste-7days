package saude.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import saude.api.api.repository.ExercicioRepository;
import saude.api.api.repository.RefeicaoRepository;
import saude.api.api.repository.SonoRepository;


@Slf4j
@Controller
@RequestMapping("/crud")
public class CrudController {

    @Autowired
    private SonoRepository sonoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    // @GetMapping
    // public String crud(Model model) {
    //     model.addAttribute("sonos", sonoRepository.findAll());
    //     model.addAttribute("exercicios", exercicioRepository.findAll());
    //     model.addAttribute("refeicoes", refeicaoRepository.findAll());
    //     return "crud";
    // }

    @GetMapping
    public ResponseEntity<?> crud(Model model) {
      try {
        model.addAttribute("sonos", sonoRepository.findAll());
        model.addAttribute("exercicios", exercicioRepository.findAll());
        model.addAttribute("refeicoes", refeicaoRepository.findAll());
        return ResponseEntity.ok().body("CRUD");
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
      }
    }

    
}
