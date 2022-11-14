import { Question } from "../payload/response/Question.js";
import { Answer } from "../payload/response/Answer.js";

class QuizUtils {

  static QUESTION_NO = 10;
  static URL_FINAL = './finalpage.html';

  static DIFF_MAP = new Map([

    [1, 2],
    [2, 3],
    [3, 4],
  ]);

  static BTN_MAP = new Map([

    [0, 'A'],
    [1, 'B'],
    [2, 'C'],
    [3, 'D'],
  ]);

  /**
   * @throws {Error}
   */
      constructor () {

      throw new Error('Constructor Not Available!');
  }

  /**
   * 
   * @param {number} diff 
   */
  static setButtons(diff) {

    [...Array(this.DIFF_MAP.get(diff)).keys()].forEach((idx) => {

      let newBtn = document.createElement('button');
      newBtn.id = `A${idx}`;
      newBtn.classList = `quiz_button`;
      newBtn.textContent = this.BTN_MAP.get(idx);
      newBtn.disabled = true;

      document.getElementById('quiz_buttons').append(newBtn);
    });
  }

  /**
   * 
   * @param {(e: Event) => void} handler 
   */
  static enableQuiz(handler) {

      document.getElementById('quiz_buttons').addEventListener('click', handler, {once: true, capture: true});
      this.enableButtons();
  }

  static disableButtons() {

      [...document.getElementsByClassName("quiz_button")].forEach((btn) => {btn.disabled = true;});
  }

  static enableButtons() {

      [...document.getElementsByClassName("quiz_button")].forEach((btn) => {btn.disabled = false;});
  }

  static removeNextButton() {

      document.getElementById('next').innerHTML = '';
  }

  static clearButtons() {

    [...document.getElementsByClassName("quiz_button")].forEach((btn) => {

      let currentBtn = btn;

      currentBtn.disabled = true;
      currentBtn.classList = 'quiz_button';
    });
  }

  static clearAnswers() {

    document.getElementById('answers_list').innerHTML = '';
  }

  /**
   * 
   * @param {(e: Event) => void} handler 
   * @param {string} id 
   * @param {string} parentId 
   * @param {string} txt 
   */
  static addButton(handler, id, parentId, txt) {

    let nextButton = document.createElement('button');
    nextButton.textContent = txt;
    nextButton.type = "button";
    nextButton.id = id;
    nextButton.addEventListener('click', handler, {once: true});

    document.getElementById(parentId).append(nextButton);
  }

  /**
   * 
   * @param {number} counter 
   * @param {Question} q 
   * @param {number} diff
   */
  static displayQuestion(counter, q, diff) {

    document.getElementById('question_count').textContent = `${counter} / ${this.QUESTION_NO}`;
    document.getElementById('question').textContent = `"${q.question} ..."`;
    document.getElementById('question_src').textContent = `${q.source}`;

    this.randomizeAnswers(q.answers, this.DIFF_MAP.get(diff));

    const answersList = document.getElementById('answers_list');
    q.answers.forEach((item) => {

      let newListItem = document.createElement('li');
      newListItem.classList = "quiz_answer";
      newListItem.textContent = item.answer;

      answersList.append(newListItem);
    });
  }

  /**
   * 
   * @param {any[]} array 
   */
  static arrayShuffle(array) {

    const iterations = 35;
    
    [...Array(iterations).keys()].forEach(() => {

      let i = Math.floor(Math.random() * array.length);
      let j = Math.floor(Math.random() * array.length);

      //swap
      [array[i], array[j]] = [array[j], array[i]];
    });
  }

  /**
   * @param {Answer[]} answers
   * @param {number} howMany 
   */
  static randomizeAnswers(answers, howMany) {

    this.arrayShuffle(answers);

    for (let i = 0; (i < answers.length) && (answers.length > howMany); i++) {
      
      if (answers[i].correct === false) {

        answers.splice(i, 1);
      }
    }

    this.arrayShuffle(answers);
  }

  /**
   * 
   * @param {number} idx 
   * @param {boolean} isCorrect 
   */
  static updateStats(idx, isCorrect) {

    window.sessionStorage.setItem(`Q${idx}`, (isCorrect)? "correct" : "wrong");
  }
}

export {QuizUtils};