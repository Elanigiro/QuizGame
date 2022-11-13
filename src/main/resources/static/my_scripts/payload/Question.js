import { Answer } from "./Answer.js";

class Question {
  
    /**
     * 
     * @param {number} idQuest 
     * @param {string} question 
     * @param {string} source 
     * @param {Answer[]} answers 
     */
    constructor(idQuest, question, source, answers) {

        this.idQuest = idQuest;
        this.question = question;
        this.source = source;
        this.answers = answers;
    }

    /**
     * 
     * @param {object} json 
     * @returns {Question}
     */
    static fromJson(json) {

        const parsedAnswers = json.answers.map((item) => Answer.fromJson(item));

        return new Question(json.idQuest, json.question, json.source, parsedAnswers);
    }
}

export {Question};