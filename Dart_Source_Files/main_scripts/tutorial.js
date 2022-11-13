import { QuizUtils } from "../utility/quizUtils";

/**
 * @param {Event} e 
 */
function clickedAnswer(e) {
  /** @type {Element}  */
  const selected = e.target;

  if (selected.classList.contains("quiz_button")){

      selected.classList.add("selected");
      //disable all buttons
      QuizUtils.disableButtons();
  }
  else {

      //I need this line in case of a click just outside of a button in order to reactivate the quiz
      QuizUtils.enableQuiz(clickedAnswer);
  }
}

function main(){

  QuizUtils.enableQuiz(clickedAnswer);
}

//start
main();