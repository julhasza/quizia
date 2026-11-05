package br.edu.quizia.controller;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String, String>> validation(MethodArgumentNotValidException ex) { return ResponseEntity.badRequest().body(Map.of("error", "Confira tema, dificuldade e quantidade de questões.")); }
  @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
  ResponseEntity<Map<String, String>> known(RuntimeException ex) { return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Map.of("error", ex.getMessage())); }
  @ExceptionHandler(Exception.class)
  ResponseEntity<Map<String, String>> unknown(Exception ex) { return ResponseEntity.status(500).body(Map.of("error", "Não foi possível gerar o quiz agora.")); }
}
