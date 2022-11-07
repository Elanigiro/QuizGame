import { Answer } from "./Answer";

class Question {
  
    /**
     * 
     * @param {number} questId 
     * @param {string} question 
     * @param {string} source 
     * @param {Answer[]} answers 
     */
    constructor(questId, question, source, answers) {

        this.questId = questId;
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

        return new Question(json.questId, json.question, json.source, parsedAnswers);
    }
}

export {Question};