class QuestionList {
  
    /**
     * 
     * @param {number} scorePerQuestion 
     * @param {number} gameVersion 
     * @param {number[]} questionCodes 
     */
    constructor(scorePerQuestion, gameVersion, questionCodes) {

        this.scorePerQuestion = scorePerQuestion;
        this.gameVersion = gameVersion;
        this.questionCodes = questionCodes;
    }

    /**
     * 
     * @param {object} json 
     * @returns {QuestionList}
     */
    static fromJson(json) {

        return new QuestionList(json.scorePerQuestion, json.gameVersion, json.questionCodes);
    }

    /**
     * 
     * @returns {string}
     */
    toString() {

        return `[Score Per Question: ${this.scorePerQuestion}, Game Version: ${this.gameVersion}, Question Codes: ${this.questionCodes}]`;
    }
}

export {QuestionList};