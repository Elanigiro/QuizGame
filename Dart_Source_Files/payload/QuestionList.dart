// ignore_for_file: file_names

class QuestionList {
  int scorePerQuestion;
  int gameVersion;
  List<int> questionCodes;

  QuestionList(
      {required this.scorePerQuestion,
      required this.gameVersion,
      required this.questionCodes});

  factory QuestionList.fromJson(Map<String, dynamic> json) {
    return QuestionList(
        scorePerQuestion: json["scorePerQuestion"],
        gameVersion: json["gameVersion"],
        questionCodes: List<int>.from(json["questionCodes"]));
  }

  @override
  String toString() {
    return "[Score Per Question: $scorePerQuestion, Game Version: $gameVersion, Question Codes: $questionCodes]";
  }
}
