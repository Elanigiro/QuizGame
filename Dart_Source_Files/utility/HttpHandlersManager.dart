// ignore_for_file: constant_identifier_names

import 'dart:convert';
import 'dart:html';
import 'package:http/http.dart' as http;
import 'QuestionStatistics.dart';
import '../payload/Question.dart';
import '../payload/QuestionList.js';
import 'viewModel.dart';

class HttpHandlersManager {
  static const String BASE_URL = "http://localhost:8197";
  static const String RESTART_URL = "http://localhost:8197/quiz/quiz.html";
  static const String URL_HOME = "http://localhost:8197/home";
  static const String URL_START = "http://localhost:8197/quiz/start";
  static const String URL_NEXT_Q = "http://localhost:8197/quiz/nextQuestion";
  static const String URL_FINAL = "http://localhost:8197/quiz/finalpage.html";
  static const String URL_RESULTS = "http://localhost:8197/quiz/results";
  static const String URL_LDRBOARD = "http://localhost:8197/home/leaderboard";

  String genericUrl = "";

  QuestionList? questions;
  int idx = 0;
  bool questionOver =
      false; // to make sure the user cannot send an answer more than once

  int totalScore = 0;

  int difficulty = 2;
  int topic = 2;

  String resString = "";
  Question? currentQuestion;

  DateTime? startTimeQuestion;

  HttpHandlersManager() {
    if (window.localStorage.containsKey("diff")) {
      difficulty = int.parse(window.localStorage["diff"]!);
    }
    if (window.localStorage.containsKey("topic")) {
      topic = int.parse(window.localStorage["topic"]!);
    }
  }

  Future<void> httpGetString(Event e) async {
    final res = await http.get(Uri.parse(genericUrl));
    resString = res.body;
  }

  Future<void> httpGetListQuest(Event e) async {
    questions = await fetchQuestionList(URL_START);
  }

  Future<void> httpGetNextQuestion(Event e) async {
    currentQuestion =
        await fetchJsonQuestion(URL_NEXT_Q, questions!.questionCodes[idx]);
  }

  Future<Question?> fetchJsonQuestion(String url, int questId) async {
    final response =
        await http.get(Uri.parse("$url?questId=$questId&diff=$difficulty"));

    return Question.fromJson(jsonDecode(response.body));
  }

  Future<QuestionList> fetchQuestionList(String url) async {
    final response =
        await http.get(Uri.parse("$url?diff=$difficulty&topic=$topic"));

    return QuestionList.fromJson(jsonDecode(response.body));
  }

  Future<void> clickedAnswer(MouseEvent me) async {
    // checks that the user is not cheating
    if (!questionOver) {
      ButtonElement selected = me.target as ButtonElement;
      selected.classes.add("selected");
      //disable all buttons
      disableButtons();
      //set the question as done
      questionOver = true;
      //set if every button is correct or not
      setCorrectButtons(this);
      //update the score
      scoreUpdate(this);

      //save question statistics in Session storage
      var qs = QuestionStatistics(
          questionNo: idx + 1,
          relativeTime: DateTime.now().difference(startTimeQuestion!),
          correct: selected.classes.contains("true"));
      qs.addObjectToStorage(window.sessionStorage);

      //increment idx and check if the game is over
      if (next()) {
        addNextButton(this);
      } else {
        setEndgameSessionStorage();
        addResultsButton(this);
      }
    }
  }

  Future<void> clickedResults(MouseEvent me) async {
    //go to final score page
    window.location.assign(URL_FINAL);
  }

  Future<void> clickedNext(MouseEvent me) async {
    removeNextButton();
    clearButtons();
    await httpGetNextQuestion(me);
    displayQuestion(this);

    questionOver = false;
  }

  bool next() {
    ++idx;
    return idx < questions!.questionCodes.length;
  }

  void setEndgameSessionStorage() {
    window.sessionStorage["game_complete"] = "true";
    window.sessionStorage["session_score"] = totalScore.toString();
    window.sessionStorage["difficulty"] = difficulty.toString();
    window.sessionStorage["topic"] = topic.toString();
    window.sessionStorage["game_version"] = questions!.gameVersion.toString();
  }
}
