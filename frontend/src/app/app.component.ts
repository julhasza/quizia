import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuizQuestion, QuizResponse, QuizService, Difficulty } from './quiz.service';

type Screen = 'home' | 'create' | 'quiz' | 'result';

@Component({ selector: 'app-root', standalone: true, imports: [CommonModule, FormsModule], templateUrl: './app.component.html' })
export class AppComponent {
  private readonly quizService = inject(QuizService);
  screen: Screen = 'home'; topic = ''; difficulty: Difficulty = 'MEDIUM'; quantity: 5 | 10 = 5;
  quiz: QuizResponse | null = null; current = 0; answers: Record<number, number> = {}; loading = false; error = '';
  readonly difficulties: { key: Difficulty; label: string }[] = [{ key: 'EASY', label: 'Fácil' }, { key: 'MEDIUM', label: 'Médio' }, { key: 'HARD', label: 'Difícil' }];
  get question(): QuizQuestion | undefined { return this.quiz?.questions[this.current]; }
  get score(): number { return this.quiz?.questions.reduce((total, q, i) => total + (this.answers[i] === q.correctOption ? 1 : 0), 0) ?? 0; }
  get answered(): number { return Object.keys(this.answers).length; }
  go(screen: Screen): void { this.error = ''; this.screen = screen; window.scrollTo({ top: 0, behavior: 'smooth' }); }
  generate(): void {
    this.error = '';
    if (!this.topic.trim()) { this.error = 'Digite um assunto para começar.'; return; }
    this.loading = true;
    this.quizService.generate({ topic: this.topic.trim(), difficulty: this.difficulty, quantity: this.quantity }).subscribe({
      next: (quiz) => { this.quiz = quiz; this.answers = {}; this.current = 0; this.loading = false; this.go('quiz'); },
      error: (err) => { this.loading = false; this.error = err?.error?.error ?? 'Não foi possível gerar o quiz. Verifique se o backend está rodando.'; }
    });
  }
  choose(index: number): void { if (this.question) this.answers[this.current] = index; }
  finish(): void { if (this.answered < (this.quiz?.questions.length ?? 0)) { this.error = 'Responda todas as questões antes de finalizar.'; return; } this.go('result'); }
  letter(index: number): string { return String.fromCharCode(65 + index); }
  restart(): void { this.topic = ''; this.quiz = null; this.answers = {}; this.current = 0; this.go('create'); }
}
