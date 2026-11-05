package br.edu.quizia.dto;

import java.util.List;

public record GenerateQuizResponse(String topic, String difficulty, boolean demo, List<QuizQuestion> questions) {}
