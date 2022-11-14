class UserScore {

    /**
     * 
     * @param {string} username 
     * @param {number} score 
     */
    constructor(username, score) {

        this.username = username;
        this.score = score;
    }

    /**
     * 
     * @param {object} json 
     * @returns {UserScore}
     */
    static fromJson(json) {

        return new UserScore(json.username, json.score);
    }
}

export {UserScore};