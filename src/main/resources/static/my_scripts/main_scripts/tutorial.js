import { QuizUtils } from "../utility/QuizUtils.js";

const answersCorrectness = [false, true, false, false];

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
      //set true or false
      [...document.getElementsByClassName('quiz_button')].forEach((element, idx) => {
        
        element.classList.add(`${answersCorrectness[idx]}`);
      });

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