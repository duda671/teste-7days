package saude.api.api.controller;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.SpringTemplateEngine;

import lombok.extern.slf4j.Slf4j;
import saude.api.api.model.Exercicio;
import saude.api.api.model.Refeicao;
import saude.api.api.model.Sono;
import saude.api.api.repository.ExercicioRepository;
import saude.api.api.repository.RefeicaoRepository;
import saude.api.api.repository.SonoRepository;

import saude.api.api.service.RelatorioService;

import java.util.Map;
import java.util.List;

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

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public String crud(Model model) {
        try {
            model.addAttribute("sonos", sonoRepository.findAll());
            model.addAttribute("exercicios", exercicioRepository.findAll());
            model.addAttribute("refeicoes", refeicaoRepository.findAll());

            model.addAttribute("exercicio", new Exercicio());
            model.addAttribute("refeicao", new Refeicao());
            model.addAttribute("sono", new Sono());

            // Adiciona a média geral de tempo ao modelo
            Double mediaTempoGeral = relatorioService.getMediaTempoGeral();
            model.addAttribute("mediaTempoGeral", mediaTempoGeral);

            // Adiciona a lista de tempos individuais ao modelo
            List<Double> temposIndividuais = relatorioService.getTemposIndividuais();
            model.addAttribute("temposIndividuais", temposIndividuais);

            // Dados das refeições
            Double mediaCaloriasGeral = relatorioService.getMediaCaloriasGeral();
            model.addAttribute("mediaCaloriasGeral", mediaCaloriasGeral);
            List<Double> caloriasIndividuais = relatorioService.getCaloriasIndividuais();
            model.addAttribute("caloriasIndividuais", caloriasIndividuais);

            // Dados do sono
            Double mediaHorasSono = relatorioService.getMediaHorasSono();
            model.addAttribute("mediaHorasSono", mediaHorasSono);
            List<Integer> horasIndividuaisSono = relatorioService.getHorasIndividuaisSono();
            model.addAttribute("horasIndividuaisSono", horasIndividuaisSono);

            return "crud";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "ERRO: " + e.getMessage();
        }

    }

}
