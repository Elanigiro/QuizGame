class QuestionList {
  
    /**
     * 
     * @param {number} gameVersion 
     * @param {number[]} questionCodes 
     */
    constructor(gameVersion, questionCodes) {

        this.gameVersion = gameVersion;
        this.questionCodes = questionCodes;
    }

    /**
     * 
     * @param {Object} json 
     * @returns {QuestionList}
     */
    static fromJson(json) {

        return new QuestionList(json.gameVersion, json.questionCodes);
    }

    /**
     * 
     * @returns {string}
     */
    toString() {

        return `[Game Version: ${this.gameVersion}, Question Codes: ${this.questionCodes}]`;
    }
}

export {QuestionList};