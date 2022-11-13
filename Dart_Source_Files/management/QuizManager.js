import { Question } from "../payload/Question";
import { QuestionList } from "../payload/QuestionList";
import { ClientSideREST } from "../utility/ClientSideREST";
import { QuizUtils } from "../utility/quizUtils";

export class QuizManager {

    /** @type {QuestionList} */
    questions;
    /** @type {number} */
    idx;

    /** @type {number} */
    totalScore;

    /** @type {number} */
    difficulty;
    /** @type {number} */
    topic;

    /** @type {Question} */
    currentQuestion;

    constructor() {

        this.difficulty = window.localStorage.getItem('diff')?? 2;
        this.topic = window.localStorage.getItem('topic')?? 2;
        this.idx = 0;
        this.totalScore = 0;

        this.startUp()
        .then(this.getNextQuestion)
        .then(() => {QuizUtils.displayQuestion(this.idx, this.currentQuestion);})
        .then(() => {QuizUtils.enableQuiz(this.clickedAnswer)})
        .catch((error) => {console.error(error);});
    }

    /**
     * @returns {Promise<any>}
     */
    async startUp() {

        window.sessionStorage.clear();
        QuizUtils.setButtons(this.difficulty);
        this.questions = await ClientSideREST.fetchQuizQuestions(QuizUtils.QUESTION_NO).questions;
    }

    /**
     * @returns {Promise<any>}
     */
    async getNextQuestion() {

        this.currentQuestion = await ClientSideREST.fetchOneQuestion(this.questions.questionCodes[idx++]);
    }

    setButtonTruth() {
        [...document.getElementsByClassName("quiz_button")].forEach((item, idx) => {
    
          item.classList.add(`${this.currentQuestion.answers[idx].correct}`);
        });
    }

    /**
     * @returns {boolean}
     */
    hasNext() {

        return this.idx < QuizUtils.QUESTION_NO;
    }

    /**
     * @param {Event} e 
     */
    clickedAnswer(e) {
        /** @type {Element}  */
        const selected = e.target;
    
        if (selected.classList.contains("quiz_button")){
    
            selected.classList.add("selected");
            //disable all buttons
            QuizUtils.disableButtons();
            //add true/false class to buttons
            setButtonTruth();

            //save the result in window.sessionStorage
            QuizUtils.updateStats(this.idx);

            if (this.hasNext()) {

                QuizUtils.addButton(this.clickedNext, 'nextButton', 'next', 'Next Question');
            }
            else {

                QuizUtils.addButton(this.clickedResults, 'resButton', 'send_results', 'Results');
            }
        }
        else {

            //I need this line in case of a click just outside of a button in order to reactivate the quiz
            QuizUtils.enableQuiz(this.clickedAnswer);
        }
    }

    /**
     * 
     * @param {Event} e 
     */
    clickedResults(e) {

        this.beforeLeaving();
        //go to final score page
        window.location = QuizUtils.URL_FINAL;
    }

    /**
     * 
     * @param {Event} e 
     */
    clickedNext(e) {

        QuizUtils.removeNextButton();
        QuizUtils.clearButtons();
        this.getNextQuestion()
        .then(() => {QuizUtils.displayQuestion(this.idx, this.currentQuestion);})
        .then(() => {QuizUtils.enableQuiz(this.clickedAnswer)})
        .catch((error) => {console.error(error);});
    }

    beforeLeaving() {
        window.sessionStorage.setItem('game_complete', 'true');
        window.sessionStorage.setItem('session_score', `${this.totalScore}`);
        window.sessionStorage.setItem('difficulty', `${this.difficulty}`);
        window.sessionStorage.setItem('topic', `${this.topic}`);
        window.sessionStorage.setItem('game_version', `${this.questions.gameVersion}`);
    }
}
