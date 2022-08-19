// ignore_for_file: file_names

class Answer {
  String answer;
  String correct;

  Answer({required this.answer, required this.correct});

  factory Answer.fromJson(Map<String, dynamic> json) {
    return Answer(
        answer: json["answer"], correct: (json["correct"]) ? "true" : "false");
  }
}
