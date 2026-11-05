package br.edu.quizia;

import br.edu.quizia.dto.*;
import br.edu.quizia.model.Difficulty;
import br.edu.quizia.service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuizServiceTest {
  @Test void generatesDemoWithAllowedQuantity() { var response = new QuizService(new DemoLlmClient()).generate(new GenerateQuizRequest("IA", Difficulty.MEDIUM, 5)); assertTrue(response.demo()); assertEquals(5, response.questions().size()); }
  @Test void rejectsUnsupportedQuantity() { assertThrows(IllegalArgumentException.class, () -> new QuizService(new DemoLlmClient()).generate(new GenerateQuizRequest("IA", Difficulty.MEDIUM, 6))); }
}
