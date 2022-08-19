// ignore_for_file: constant_identifier_names

import 'dart:convert';
import 'dart:html';
import '../payload/UserScore.dart';
import '../utility/HttpHandlersManager.dart';
import 'package:http/http.dart' as http;

const int MAX_NAME_LENGTH = 12;

void main() {
  redirectIfNotAllowed();

  showScore();
  showStats();
  setRetry();
  bool stillToSend = setSend();

  if (stillToSend) {
    //input validation and submit handlers
    addInputHandlers();

    //manually trigger validation after loading the page
    (querySelector("#username_input") as InputElement)
        .dispatchEvent(Event('input'));
  }
}

void redirectIfNotAllowed() {
  if (!window.sessionStorage.containsKey("game_complete")) {
    window.location.assign(HttpHandlersManager.URL_HOME);
  }
}

DivElement generateStatBox(String correct, int number) {
  DivElement main = DivElement()..classes.addAll(["stat-box", correct]);
  DivElement second = DivElement()
    ..classes.add("number")
    ..text = number.toString();
  SpanElement third = SpanElement()
    ..classes.add("material-symbols-rounded")
    ..text = (correct == "correct") ? "done" : "close";

  main.children.addAll([second, third]);

  return main;
}

void showStats() {
  int i = 1;
  while (window.sessionStorage.containsKey("Q$i")) {
    List<String> values = window.sessionStorage["Q$i"]!.split(';');
    querySelector("#cards")!.children.add(generateStatBox(values[1], i));
    ++i;
  }
}

void restartQuiz() {
  window.location.assign(HttpHandlersManager.RESTART_URL);
}

Future<void> clickedRestart(MouseEvent me) async {
  restartQuiz();
}

void setRetry() {
  (querySelector("#retry") as ButtonElement).onClick.listen(clickedRestart);
}

//returns false if result already sent
bool setSend() {
  // if the user already sent a username previously on this browser
  if (window.localStorage.containsKey("username")) {
    (querySelector("#username_input") as InputElement).value =
        window.localStorage["username"];
  }

  // it checks if the user is trying to send the score of the
  // same game more than once
  if (window.sessionStorage.containsKey("already_sent")) {
    updateSendResult(true);
    return false;
  }

  (querySelector("#send_result") as ButtonElement).onClick.listen(clickedSend);
  return true;
}

void showScore() {
  String? score = window.sessionStorage["session_score"];

  (querySelector("#score") as HeadingElement).text =
      "${(score != null) ? score : "0"} points";
}

Future<http.Response> sendResults() async {
  return await http.post(Uri.parse(HttpHandlersManager.URL_RESULTS),
      body: jsonEncode(UserScore(
          username: window.localStorage["username"]!,
          result: int.parse(window.sessionStorage["session_score"]!),
          difficulty: int.parse(window.sessionStorage["difficulty"]!),
          topic: int.parse(window.sessionStorage["topic"]!),
          gameVersion: int.parse(window.sessionStorage["game_version"]!))),
      headers: {"Content-Type": "application/json; charset=utf-8"});
}

void updateSendResult(bool status) {
  //remove form elements
  querySelector("#result_div")!.children.clear();
  //create new element
  SpanElement response = SpanElement()
    ..id = "span_result"
    ..text = (status) ? "Result Saved!" : "Unexpected Error! Try again later!";
  //add new element
  querySelector("#result_div")!.children.add(response);
}

Future<void> clickedSend(Event me) async {
  window.localStorage["username"] =
      (querySelector("#username_input") as InputElement).value!;

  http.Response res = await sendResults();

  updateSendResult(res.statusCode == HttpStatus.created);

  if (res.statusCode == HttpStatus.created) {
    window.sessionStorage["already_sent"] = "true";
  }
}

void addInputHandlers() {
  (querySelector("#username_input") as InputElement)
    ..onInput.listen(inputValidation)
    ..onKeyDown.listen(submitInput);
}

Future<void> inputValidation(Event e) async {
  bool validState = false;

  //removes whitespaces
  String content = (e.target as InputElement).value!;
  content = content.replaceAll(RegExp(r"\s+"), "");
  (e.target as InputElement).value = content;

  //check if username is too long
  if (content.length > MAX_NAME_LENGTH) {
    (querySelector(".tooltip") as DivElement).classes.add("invalid");
  } else {
    (querySelector(".tooltip") as DivElement)
        .classes
        .retainWhere((element) => element == "tooltip");

    //check if username is not empty
    if (content.isNotEmpty) {
      validState = true;
    }
  }

  //set button status according to username validity
  if (validState) {
    (querySelector("#send_result") as ButtonElement).disabled = false;
  } else {
    (querySelector("#send_result") as ButtonElement).disabled = true;
  }
}

Future<void> submitInput(KeyboardEvent ke) async {
  // send the result to the server if user pressed Enter
  // and the Username is valid
  if ((ke.key == "Enter") &&
      !(querySelector("#send_result") as ButtonElement).disabled) {
    await clickedSend(ke);
  }
}
