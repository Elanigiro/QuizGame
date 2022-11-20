import { DiffVersionScore } from "../payload/response/DiffVersionScore.js";
import { Question } from "../payload/response/Question.js";
import { QuestionList } from "../payload/response/QuestionList.js";
import { ClientSideREST } from "../utility/ClientSideREST.js";
import { QuizUtils } from "../utility/QuizUtils.js";

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
    /** @type {DiffVersionScore} */
    diffVersionScore;

    /** @type {Question} */
    currentQuestion;

    constructor() {

        this.difficulty = parseInt(window.localStorage.getItem('diff'))?? 2;
        this.topic = parseInt(window.localStorage.getItem('topic'))?? 2;
        this.diffVersionScore = null; //flag value
        this.idx = 0;
        this.totalScore = 0;

        this.startUp()
        .then(() => this.getNextQuestion())
        .then(() => {QuizUtils.displayQuestion(this.idx, this.currentQuestion, this.difficulty);})
        .then(() => {QuizUtils.enableQuiz((e) => {this.clickedAnswer(e);})})
        .catch((error) => {console.error(error);});
    }

    /**
     * @returns {Promise<undefined>}
     */
    async startUp() {

        window.sessionStorage.clear();
        QuizUtils.setButtons(this.difficulty);
        this.questions = await ClientSideREST.fetchQuizQuestionList(this.topic, QuizUtils.QUESTION_NO, true);
        this.scorePerQuestion = await ClientSideREST.fetchDiffVersionScore(this.questions.gameVersion, this.difficulty);
    }

    /**
     * @returns {Promise<undefined>}
     */
    async getNextQuestion() {

        this.currentQuestion = await ClientSideREST.fetchOneQuestion(this.questions.questionCodes[this.idx++]);
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
            this.setButtonTruth();
            //verify correctness
            const isCorrect = (document.querySelector('.quiz_button.selected.true'))? true : false;
            //update score
            this.totalScore += (isCorrect)? this.scorePerQuestion.scorePerQuest : 0;
            //save the result in window.sessionStorage
            QuizUtils.updateStats(this.idx, isCorrect);

            if (this.hasNext()) {

                QuizUtils.addButton((e) => {this.clickedNext(e);}, 'nextButton', 'next', 'Next Question');
            }
            else {

                QuizUtils.addButton((e) => {this.clickedResults(e);}, 'resButton', 'send_results', 'Results');
            }
        }
        else {

            //I need this line in case of a click just outside of a button in order to reactivate the quiz
            QuizUtils.enableQuiz((e) => {this.clickedAnswer(e);});
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
        QuizUtils.clearAnswers();
        
        this.getNextQuestion()
        .then(() => {QuizUtils.displayQuestion(this.idx, this.currentQuestion, this.difficulty);})
        .then(() => {QuizUtils.enableQuiz((e) => {this.clickedAnswer(e);})})
        .catch((error) => {console.error(error);});
    }

    beforeLeaving() {
        window.sessionStorage.setItem('game_complete', 'true');
        window.sessionStorage.setItem('session_score', `${this.totalScore}`);
        window.sessionStorage.setItem('pool_size', `${QuizUtils.QUESTION_NO}`);
        window.sessionStorage.setItem('difficulty', `${this.difficulty}`);
        window.sessionStorage.setItem('topic', `${this.topic}`);
        window.sessionStorage.setItem('game_version', `${this.questions.gameVersion}`);
    }
}
