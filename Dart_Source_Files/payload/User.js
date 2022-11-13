class User {

    static MAX_NAME_LENGTH = 12;

    /**
     * 
     * @param {string} id 
     * @param {string} username 
     * @throws {Error} in case of undefined arguments
     */
    constructor(id, username) {

        if ((id === undefined) || (username === undefined)) {

            throw new Error('Invalid Arguments!');
        }

        this.id = id;
        this.username = username;
    }

    /**
     * 
     * @param {object} json 
     * @returns {User}
     */
    static fromJson(json) {

        return new UserScore(json.id, json.username);
    }
}

export {User};