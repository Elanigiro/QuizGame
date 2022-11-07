/**
 * @param {Event} e 
 */
 function clickedAnswer(e) {
    /** @type {Element}  */
    const selected = e.target;
  
    if (selected.classList.contains("quiz_button")){
  
      selected.classList.add("selected");
      //disable all buttons
      disableButtons();
      //add true/false class to buttons
      setButtonTruth();
    }
  }

  function setCorrectButtons() {
    ElementList<ButtonElement> buttonList = querySelectorAll(".quiz_button");
    for (int i = 0; i < buttonList.length; ++i) {
      buttonList[i].classes.add(man.currentQuestion!.answers[i].correct);
    }
  }
  
  void displayQuestion(HttpHandlersManager man) {
    (querySelector("#question_count") as SpanElement).text =
        "${man.idx + 1} / ${man.questions!.questionCodes.length}";
    (querySelector("#question") as HeadingElement).text =
        "\"${man.currentQuestion!.question} ...\"";
    (querySelector("#question_src") as HeadingElement).text =
        man.currentQuestion!.source;
  
    ElementList<SpanElement> answerList = querySelectorAll(".quiz_answer");
    for (int i = 0; i < answerList.length; ++i) {
      answerList[i].text = man.currentQuestion!.answers[i].answer;
    }
  
    //resets stopwatch for the question
    man.startTimeQuestion = DateTime.now();
  }
  
  void disableButtons() {
    for (ButtonElement button in querySelectorAll(".quiz_button")) {
      button.disabled = true;
    }
  }
  
  void clearButtons() {
    for (ButtonElement button in querySelectorAll(".quiz_button")) {
      button
        ..classes.retainAll(["quiz_button"])
        ..disabled = false;
    }
  }
  
  void addNextButton(HttpHandlersManager man) {
    ButtonElement nextButton = ButtonElement()
      ..text = "Next Question"
      ..type = "button"
      ..id = "nextButton";
  
    querySelector("#next")!.children.add(nextButton);
    querySelector("#nextButton")!.onClick.listen(man.clickedNext);
  }
  
  void removeNextButton() {
    querySelector("#next")!.children.clear();
  }
  
  void scoreUpdate(HttpHandlersManager man) {
    if (querySelector(".selected.true") != null) {
      man.totalScore += man.questions!.scorePerQuestion;
    }
  }
  
  void addResultsButton(HttpHandlersManager man) {
    ButtonElement resultsButton = ButtonElement()
      ..text = "Results"
      ..type = "button"
      ..id = "resButton"
      ..onClick.listen(man.clickedResults);
  
    querySelector("#send_results")!.children.add(resultsButton);
  }
  
  void pageRebuild() {
    querySelector("body")!.children.clear();
  }
  
  LIElement singleListItem({required String id, required String spanClass}) {
    SpanElement tmp = SpanElement()
      ..id = id
      ..classes.add(spanClass);
    return LIElement()..children.add(tmp);
  }
  
  void addAnswers(int howMany) {
    OListElement parent = querySelector(".answers_list") as OListElement;
  
    for (int i = 1; i <= howMany; ++i) {
      parent.children
          .add(singleListItem(id: "A${i}_text", spanClass: "quiz_answer"));
    }
  }
  
  ButtonElement singleButton(
      {required String id,
      required String buttonClass,
      required String content}) {
    return ButtonElement()
      ..id = id
      ..classes.add(buttonClass)
      ..type = "button"
      ..text = content;
  }
  
  void addAnswersButtons(int howMany) {
    DivElement parent = querySelector(".buttons") as DivElement;
  
    for (int i = 1; (i <= howMany) && (i <= ALPHABET.length); ++i) {
      parent.children.add(singleButton(
          id: "A$i", buttonClass: "quiz_button", content: ALPHABET[i - 1]));
    }
  }
  
  // ignore: constant_identifier_names
  const String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  
  void setEventListenersButtons(HttpHandlersManager man) {
    for (ButtonElement button in querySelectorAll(".quiz_button")) {
      button.onClick.listen(man.clickedAnswer);
    }
  }
  