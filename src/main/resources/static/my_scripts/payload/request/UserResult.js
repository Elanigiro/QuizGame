class UserResult {

    /**
     * @param {number} score
     * @param {number} questionPoolSize 
     * @param {number} difficulty 
     * @param {number} topic 
     * @param {number} gameVersion 
     */
    constructor(score, questionPoolSize, difficulty, topic, gameVersion) {

        this.score = score;
        this.questionPoolSize = questionPoolSize;
        this.difficulty = difficulty;
        this.topic = topic;
        this.gameVersion = gameVersion;
    }

    /**
     * 
     * @param {object} json 
     * @returns {UserResult}
     */
    static fromJson(json) {

        return new UserResult(json.score, json.questionPoolSize, json.difficulty, json.topic, json.gameVersion);
    }
}

export {UserResult};