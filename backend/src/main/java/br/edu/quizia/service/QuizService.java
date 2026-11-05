package br.edu.quizia.service;

import br.edu.quizia.dto.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {
  private final LlmClient client;
  public QuizService(LlmClient client) { this.client = client; }
  public GenerateQuizResponse generate(GenerateQuizRequest request) {
    if (request.quantity() != 5 && request.quantity() != 10) throw new IllegalArgumentException("A quantidade deve ser 5 ou 10.");
    List<QuizQuestion> questions = client.generate(request);
    if (questions.size() != request.quantity() || questions.stream().anyMatch(q -> !q.isValidQuestion())) throw new IllegalStateException("A IA retornou questões inválidas.");
    return new GenerateQuizResponse(request.topic().trim(), request.difficulty().name(), client instanceof DemoLlmClient, questions);
  }
}
