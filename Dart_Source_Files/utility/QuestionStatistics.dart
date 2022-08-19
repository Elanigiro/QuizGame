// ignore_for_file: file_names

import 'dart:html';

class QuestionStatistics {
  final int questionNo;
  Duration relativeTime;
  bool correct;

  QuestionStatistics(
      {required this.questionNo,
      required this.relativeTime,
      required this.correct});

  void addObjectToStorage(Storage st) {
    st["Q$questionNo"] =
        "${roundDuration()};${(correct) ? "correct" : "wrong"}";
  }

  // round to 100ths of a second
  double roundDuration() {
    return ((relativeTime.inMilliseconds as double) / 10).roundToDouble() / 100;
  }
}
