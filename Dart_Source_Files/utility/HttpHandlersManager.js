
class HttpHandlersManager {
  static BASE_URL = "http://localhost:8197";
  static RESTART_URL = "http://localhost:8197/quiz/quiz.html";
  static URL_HOME = "http://localhost:8197/home";
  static URL_START = "http://localhost:8197/quiz/start";
  static URL_NEXT_Q = "http://localhost:8197/quiz/nextQuestion";
  static URL_FINAL = "http://localhost:8197/quiz/finalpage.html";
  static URL_RESULTS = "http://localhost:8197/quiz/results";
  static URL_LDRBOARD = "http://localhost:8197/home/leaderboard";

  constructor() {

    this.difficulty = parseInt(window.localStorage.getItem("diff")) || 2;
    this.topic = parseInt(window.localStorage.getItem("topic")) || 2;

    this.totalScore = 0;

    this.questionOver = false; //to prevent some kind of cheaters

    /** @type {QuestionList} */
    this.questions = null;
    this.idx = 0;

    this.resString = "";
    /** @type {Question} */
    this.currentQuestion = null;
  }

  async fetchQuestionList() {

    let response = await fetch(`${HttpHandlersManager.URL_START}?diff=${this.difficulty}&topic=${this.topic}`);

    if (!response.ok) {

      throw new Error(`Response status ${response.status}!`);
    }

    let resJson = await response.json();

    return new QuestionList(resJson);
  }

  /**
   * @param {number} questId 
   */
  async fetchJsonQuestion(questId) {

    if (!parseInt(questId)) {

      throw new Error(`Invalid questId ${questId}`);
    }
    
    let response = await fetch(`${HttpHandlersManager.URL_NEXT_Q}?questId=${questId}&diff=${this.difficulty}`);

    if (!response.ok) {

      throw new Error(`Response status ${response.status}!`);
    }

    let resJson = await response.json();

    return new Question(resJson);
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
