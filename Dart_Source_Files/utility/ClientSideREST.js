import { Question } from "../payload/Question";
import { UserScore } from "../payload/UserScore";

/**
 * Static class to handle all REST requests/responses to the Back-End
 */
class ClientSideREST {

    static URL_QUIZ_Q = "./quiz/questions";
    static URL_LDRBOARD = "./users?leaderboard=true";

    /**
     * @throws {Error}
     */
    constructor () {

        throw new Error('Constructor Not Available!');
    }

    /**
     * 
     * @param {number} id 
     * @returns {Promise<Question>}
     */
    static async fetchOneQuestion(id) {

        if ((parseInt(id) !== id) || (id <= 0)) {

            throw new Error('id must be a positive integer!');
        }

        const res = await fetch(`${this.URL_QUIZ_Q}/${id}`);

        if (!res.ok) {

            throw new Error(`Request was unsuccessful!\n Code: ${res.status}`);
        }

        const responseJson = await res.json();

        return Question.fromJson(responseJson);
    }

    /**
     * @param {number} limit
     * @returns {Promise<Question[]>}
     */
    static async fetchQuizQuestions(limit) {

        if ((parseInt(limit) !== limit) || (limit <= 0)) {

            throw new Error('limit must be a positive integer!');
        }

        const res = await fetch(`${this.URL_QUIZ_Q}?limit=${limit}`);

        if (!res.ok) {

            throw new Error(`Request was unsuccessful!\n Code: ${res.status}`);
        }

        const responseJson = await res.json();

        return responseJson.map((item) => Question.fromJson(item));
    }

    /**
     * @returns {Promise<UserScore[]>}
     */
    static async fetchLeaderboard() {

        const res = await fetch(`${this.URL_LDRBOARD}`);

        if (!res.ok) {

            throw new Error(`Request was unsuccessful!\n Code: ${res.status}`);
        }

        const responseJson = await res.json();

        return responseJson.map((item) => UserScore.fromJson(item));
    }
}

export {ClientSideREST};