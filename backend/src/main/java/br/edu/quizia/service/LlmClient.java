package br.edu.quizia.service;

import br.edu.quizia.dto.GenerateQuizRequest;
import br.edu.quizia.dto.QuizQuestion;
import java.util.List;

public interface LlmClient { List<QuizQuestion> generate(GenerateQuizRequest request); }
