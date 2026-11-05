package br.edu.quizia.controller;

import br.edu.quizia.dto.*;
import br.edu.quizia.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:5173"})
public class QuizController {
  private final QuizService service;
  public QuizController(QuizService service) { this.service = service; }
  @PostMapping("/generate") public ResponseEntity<GenerateQuizResponse> generate(@Valid @RequestBody GenerateQuizRequest request) { return ResponseEntity.ok(service.generate(request)); }
}
