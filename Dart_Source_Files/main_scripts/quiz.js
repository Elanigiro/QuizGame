
void main() async {
  HttpHandlersManager manager = HttpHandlersManager();

  //reset current session storage
  window.sessionStorage.clear();

  //ask for question list
  await manager.httpGetListQuest(Event("none"));
  //ask for first question
  await manager.httpGetNextQuestion(Event("none"));

  //add text fields and buttons
  addAnswers(manager.currentQuestion!.answers.length);
  addAnswersButtons(manager.currentQuestion!.answers.length);
  //show everything I gathered on the page
  displayQuestion(manager);

  //set eventListeners
  setEventListenersButtons(manager);
}
