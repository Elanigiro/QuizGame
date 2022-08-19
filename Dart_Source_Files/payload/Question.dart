// ignore_for_file: file_names

import 'Answer.dart';

class Question {
  final int questId;
  String question;
  String source;
  List<Answer> answers;

  Question(
      {required this.questId,
      required this.question,
      required this.source,
      required this.answers});

  factory Question.fromJson(Map<String, dynamic> json) {
    return Question(
        questId: json['idQuest'],
        question: json['question'],
        source: json['source'],
        answers: List<Answer>.from(
            (json['answers'] as Iterable).map((e) => Answer.fromJson(e))));
  }
}
