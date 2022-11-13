import { Question } from "../payload/Question.js";

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

    [...Array(this.DIFF_MAP[diff]).keys()].forEach((idx) => {

      let newBtn = document.createElement('btn');
      newBtn.id = `A${idx}`;
      newBtn.classList = `quiz_button`;
      newBtn.textContent = this.BTN_MAP[idx];
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

      [...document.getElementsByClassName("quiz_button")].forEach((btn) => {(new HTMLButtonElement(btn)).disabled = true;});
  }

  static enableButtons() {

      [...document.getElementsByClassName("quiz_button")].forEach((btn) => {(new HTMLButtonElement(btn)).disabled = true;});
  }

  static removeNextButton() {

      document.getElementById('next').innerHTML = '';
  }

  static clearButtons() {

    [...document.getElementsByClassName("quiz_button")].forEach((btn) => {

      let currentBtn = new HTMLButtonElement(btn);

      currentBtn.disabled = true;
      currentBtn.classList = 'quiz_button';
    });
  }

  /**
   * 
   * @param {(e: Event) => void} handler 
   * @param {string} id 
   * @param {string} parentId 
   * @param {string} txt 
   */
  static addButton(handler, id, parentId, txt) {

    let nextButton = new HTMLButtonElement(document.createElement('btn'));
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
   */
  static displayQuestion(counter, q) {

    document.getElementById('question_count').textContent = `${counter} / ${this.QUESTION_NO}`;
    document.getElementById('question').textContent = `"${q.question} ..."`;
    document.getElementById('question_src').textContent = `${q.source}`;

    [...document.getElementsByClassName('quiz_answer')].forEach((item, idx) => {

      item.textContent = q.answers[idx].answer;
    });
  }

  /**
   * 
   * @param {number} idx 
   */
  static updateStats(idx) {

    window.sessionStorage.setItem(`Q${idx}`, (document.querySelector('.quiz_button.selected.true'))? "correct" : "wrong");
  }
}

export {QuizUtils};