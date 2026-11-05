package br.edu.quizia.dto;

import br.edu.quizia.model.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GenerateQuizRequest(@NotBlank @Size(max = 160) String topic, @NotNull Difficulty difficulty, @NotNull Integer quantity) {}
