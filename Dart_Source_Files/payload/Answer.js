class Answer {  

    /**
     * 
     * @param {string} answer 
     * @param {boolean} correct 
     */
    constructor(answer, correct) {

        this.answer = answer;
        this.correct = correct;
    }

    /**
     * 
     * @param {object} json 
     * @returns {Answer}
     */
    static fromJson(json) {

        return new Answer(json.answer, json.correct);
    }
}

export {Answer};