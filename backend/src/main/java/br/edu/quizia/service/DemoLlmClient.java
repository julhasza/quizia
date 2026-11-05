package br.edu.quizia.service;

import br.edu.quizia.dto.GenerateQuizRequest;
import br.edu.quizia.dto.QuizQuestion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ConditionalOnExpression("'${llm.api-key:}'.length() == 0")
public class DemoLlmClient implements LlmClient {
  @Override public List<QuizQuestion> generate(GenerateQuizRequest request) { return DemoQuizFactory.questions(request.quantity()); }
}
