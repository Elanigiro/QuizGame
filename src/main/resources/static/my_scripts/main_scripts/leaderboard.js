import { ClientSideREST } from "../utility/ClientSideREST.js";
import { UserScore } from "../payload/UserScore.js";

/**
 * 
 * @param {number} idx 
 * @param {UserScore} us 
 * @returns {HTMLParagraphElement}
 */
function buildLBRecord(idx, us) {

  let sp1 = document.createElement('span');
  let sp2 = document.createElement('span');
  let sp3 = document.createElement('span');

  sp1.textContent = String(idx);
  sp2.textContent = us.username;
  sp3.textContent = String(us.score);

  let leaderboardRecord = document.createElement('p');
  leaderboardRecord.append(sp1, sp2, sp3);

  return leaderboardRecord;
}

/**
 * 
 * @param {UserScore[]} leaderboard 
 */
function displayRecords(leaderboard) {
  let tbody = document.getElementById("leaderboard_records");

  leaderboard.forEach((item, idx) => {

    tbody.append(buildLBRecord(idx + 1, item));
  });
}

function main() {
  
  ClientSideREST.fetchLeaderboard()
  .then((leaderboard) => { displayRecords(leaderboard); })
  .catch((err) => {console.error(err);})
}

//start
main();