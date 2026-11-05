package br.edu.quizia.service;

import br.edu.quizia.dto.QuizQuestion;
import java.util.List;

public final class DemoQuizFactory {
  private DemoQuizFactory() {}
  public static List<QuizQuestion> questions(int quantity) {
    var all = List.of(
      new QuizQuestion("Qual é o principal objetivo do aprendizado de máquina supervisionado?", List.of("Encontrar padrões sem referência", "Aprender relações a partir de exemplos rotulados", "Substituir decisões humanas", "Armazenar todos os dados"), 1, "O algoritmo aprende com exemplos que já possuem rótulos conhecidos."),
      new QuizQuestion("O que representa o peso de uma conexão em uma rede neural?", List.of("A influência relativa de uma entrada", "O número de camadas", "A quantidade de dados", "O tempo de treinamento"), 0, "Pesos controlam quanto cada entrada influencia o cálculo do neurônio."),
      new QuizQuestion("Qual situação é um exemplo de processamento de linguagem natural?", List.of("Aumentar a memória", "Renderizar uma imagem", "Classificar o sentimento de um texto", "Medir a temperatura"), 2, "Análise de sentimento interpreta linguagem humana escrita."),
      new QuizQuestion("O que significa overfitting?", List.of("Não ter dados", "Aprender o treino e generalizar mal", "Produzir respostas aleatórias", "Usar poucos parâmetros"), 1, "O modelo memoriza o treino e perde desempenho em dados novos."),
      new QuizQuestion("Por que separar dados de teste do treinamento?", List.of("Avaliar generalização", "Aumentar dados artificialmente", "Remover erros", "Evitar função de custo"), 0, "Dados não vistos permitem uma avaliação mais honesta."),
      new QuizQuestion("O que torna uma questão gerada por IA adequada?", List.of("Várias respostas corretas", "Ser impossível sem contexto", "Enunciado claro e uma resposta correta", "Termos sem explicação"), 2, "Clareza e correção única tornam a avaliação objetiva."),
      new QuizQuestion("Qual prática ajuda a reduzir vieses em IA?", List.of("Ignorar os dados", "Usar dados diversos e revisar resultados", "Remover validação humana", "Treinar com um exemplo"), 1, "Dados representativos e revisão ajudam a identificar padrões injustos."),
      new QuizQuestion("O que é uma API?", List.of("Uma forma padronizada de sistemas se comunicarem", "Uma placa de vídeo", "Uma linguagem de banco", "Um arquivo de imagem"), 0, "APIs definem regras e formatos para troca de dados e funcionalidades."),
      new QuizQuestion("O que deixa um prompt mais consistente?", List.of("Remover o objetivo", "Especificar formato, contexto e restrições", "Usar palavras soltas", "Pedir contradições"), 1, "Instruções específicas reduzem ambiguidades."),
      new QuizQuestion("Qual o papel de uma função de ativação?", List.of("Introduzir não linearidade", "Apagar dados", "Definir internet", "Escolher idioma"), 0, "A não linearidade permite representar relações mais complexas."));
    return all.subList(0, quantity);
  }
}
