class DiffVersionScore {  

    /**
     * 
     * @param {number} idDiff 
     * @param {number} idVersion
     * @param {number} scorePerQuest
     */
    constructor(idDiff, idVersion, scorePerQuest) {

        this.idDiff = idDiff;
        this.idVersion = idVersion;
        this.scorePerQuest = scorePerQuest;
    }

    /**
     * 
     * @param {object} json 
     * @returns {DiffVersionScore}
     */
    static fromJson(json) {

        return new DiffVersionScore(json.idDiff, json.idVersion, json.scorePerQuest);
    }
}

export {DiffVersionScore};