class UserScore {

    /**
     * 
     * @param {string} username 
     * @param {number} score 
     * @param {number} difficulty 
     * @param {number} topic 
     * @param {number} gameVersion 
     */
    constructor(username, score, difficulty = undefined, topic = undefined, gameVersion = undefined) {

        this.username = username;
        this.score = score;
        this.difficulty = difficulty;
        this.topic = topic;
        this.gameVersion = gameVersion;
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