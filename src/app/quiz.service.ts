import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export type Difficulty = 'EASY' | 'MEDIUM' | 'HARD';
export interface QuizQuestion { statement: string; options: string[]; correctOption: number; explanation: string; }
export interface QuizRequest { topic: string; difficulty: Difficulty; quantity: 5 | 10; }
export interface QuizResponse { topic: string; difficulty: string; demo: boolean; questions: QuizQuestion[]; }

@Injectable({ providedIn: 'root' })
export class QuizService {
  private readonly http = inject(HttpClient);
  private readonly url = 'http://localhost:8080/api/quizzes/generate';
  generate(request: QuizRequest): Observable<QuizResponse> { return this.http.post<QuizResponse>(this.url, request); }
}
