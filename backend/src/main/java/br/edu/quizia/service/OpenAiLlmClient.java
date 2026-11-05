package br.edu.quizia.service;

import br.edu.quizia.dto.GenerateQuizRequest;
import br.edu.quizia.dto.QuizQuestion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.net.http.HttpClient;
import java.time.Duration;

@Component
@ConditionalOnExpression("'${llm.api-key:}'.length() > 0")
public class OpenAiLlmClient implements LlmClient {
  private final RestClient client;
  private final ObjectMapper mapper;
  private final String model;
  public OpenAiLlmClient(ObjectMapper mapper, @Value("${llm.api-key}") String apiKey, @Value("${llm.base-url}") String baseUrl, @Value("${llm.model}") String model, @Value("${llm.timeout-seconds:30}") int timeoutSeconds) {
    this.mapper = mapper; this.model = model;
    var httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(timeoutSeconds)).build();
    var requestFactory = new JdkClientHttpRequestFactory(httpClient);
    requestFactory.setReadTimeout(Duration.ofSeconds(timeoutSeconds));
    this.client = RestClient.builder().requestFactory(requestFactory).baseUrl(baseUrl).defaultHeader("Authorization", "Bearer " + apiKey).build();
  }
  @Override public List<QuizQuestion> generate(GenerateQuizRequest request) {
    String prompt = "Gere " + request.quantity() + " questões sobre '" + request.topic() + "', dificuldade " + request.difficulty() + ". Retorne somente JSON no formato {\\\"questions\\\":[{\\\"statement\\\":\\\"...\\\",\\\"options\\\":[\\\"A\\\",\\\"B\\\",\\\"C\\\",\\\"D\\\"],\\\"correctOption\\\":0,\\\"explanation\\\":\\\"...\\\"}]}. correctOption é um índice de 0 a 3.";
    try {
      String body = client.post().uri("/chat/completions").contentType(MediaType.APPLICATION_JSON).body(Map.of("model", model, "temperature", 0.4, "response_format", Map.of("type", "json_object"), "messages", List.of(Map.of("role", "system", "content", "Você é um gerador educacional. Seja factual e retorne JSON válido."), Map.of("role", "user", "content", prompt)))).retrieve().body(String.class);
      JsonNode root = mapper.readTree(body); String content = root.at("/choices/0/message/content").asText();
      List<QuizQuestion> questions = new ArrayList<>();
      for (JsonNode node : mapper.readTree(content).at("/questions")) questions.add(mapper.convertValue(node, QuizQuestion.class));
      return questions;
    } catch (Exception ex) { throw new IllegalStateException("O provedor de IA está indisponível ou retornou JSON inválido."); }
  }
}
