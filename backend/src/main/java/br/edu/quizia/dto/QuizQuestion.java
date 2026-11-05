package br.edu.quizia.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record QuizQuestion(@NotBlank String statement, @NotEmpty List<@NotBlank String> options, int correctOption, @NotBlank String explanation) {
  @AssertTrue(message = "cada questão precisa ter exatamente quatro alternativas e uma resposta válida")
  public boolean isValidQuestion() { return options != null && options.size() == 4 && correctOption >= 0 && correctOption < 4 && options.stream().map(String::trim).distinct().count() == 4; }
}
