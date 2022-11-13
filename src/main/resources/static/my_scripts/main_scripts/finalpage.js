import { User } from "../payload/User.js";
import { UserScore } from "../payload/UserScore.js";
import { ClientSideREST } from "../utility/ClientSideREST.js";

function redirectIfNotAllowed() {
  if (!window.sessionStorage.getItem("game_complete")) {
    window.location.assign('/');
  }
}

/**
 * 
 * @param {string} correct 
 * @param {number} number 
 * @returns {HTMLDivElement}
 */
function generateStatBox(correct, number) {

  let currentRoot = document.createElement('div');
  currentRoot.classList = `stat-box ${correct}`;

  let firstChild = document.createElement('div');
  firstChild.classList = 'number';
  firstChild.textContent = `${number}`;

  let secondChild = document.createElement('span');
  secondChild.classList = 'material-symbols-rounded';
  secondChild.textContent = (correct == "correct") ? "done" : "close";

  currentRoot.append(firstChild, secondChild);

  return currentRoot;
}

function showStats() {

  let i = 1;
  let item;
  while (item = window.sessionStorage.getItem(`Q${i}`)) {

    document.getElementById("cards").append(generateStatBox(item, i));
    ++i;
  }
}


/**
 * 
 * @param {Event} e 
 */
function clickedRestart(e) {

  window.location.assign('/quiz/quiz.html');
}

function setRetry() {
  document.getElementById('retry').onclick = clickedRestart;
}

function setSend() {
  let name;
  // if the user already sent a username previously on this browser
  if (name = window.localStorage.getItem('username')) {

    document.getElementById("username_input").setAttribute('value', name);
  }

  document.getElementById("send_result").addEventListener('click', clickedSend, {once: true});
}

/**
 * 
 * @returns {Promise<Response>}
 */
function sendResults() {

  return ClientSideREST.sendUserScore(UserScore.fromJson({

    username: window.localStorage.getItem('username'),
    score: parseInt(window.sessionStorage.getItem('session_score')),
    difficulty: parseInt(window.sessionStorage.getItem('difficulty')),
    topic: parseInt(window.sessionStorage.getItem('topic')),
    gameVersion: parseInt(window.sessionStorage.getItem('game_version')),
  }));
}

async function clickedSend(e) {

  window.localStorage.setItem("username", document.getElementById("username_input").value);

  const res = await sendResults();

  updateSendResult(res.ok);
}

/**
 * 
 * @param {boolean} status 
 */
function updateSendResult(status) {

  if (status) {

    window.sessionStorage.setItem('success', 'true');
  }

  const resultDiv = document.getElementById("result_div");
  //remove form elements
  resultDiv.innerHTML = '';
  //create new span element
  const mySpan = document.createElement('span');
  mySpan.id = 'span_result';
  mySpan.textContent = (status) ? "Result Saved!" : "Unexpected Error! Try again later!";
  //append span element
  resultDiv.append(mySpan);
}

function showScore() {

  document.getElementById("score").textContent = window.sessionStorage.getItem('session_score');
}

function addInputHandlers() {

  const usernameInput = document.getElementById('username_input');
  usernameInput.addEventListener('input', inputValidation);
  usernameInput.addEventListener('keydown', submitInput);
}

/**
 * 
 * @param {Event} e 
 */
function inputValidation(e) {
  let validState = false;

  //removes whitespaces
  let content = String(e.currentTarget.value);
  content.replaceAll(/\s+/, '');
  e.currentTarget.value = content;

  //check if username is too long
  if(content.length > User.MAX_NAME_LENGTH) {

    document.getElementsByClassName('.tooltip')[0].classList.add('invalid');
  }
  else {

    document.getElementsByClassName('.tooltip')[0].classList.remove('invalid');
    //check if username is not empty
    if (content) {
      validState = true;
    }
  }

  //set button status according to username validity
  if (validState) {

    document.getElementById('send_result').disabled = false;
  }
  else {
    
    document.getElementById('send_result').disabled = true;
  }
}

/**
 * 
 * @param {KeyboardEvent} ke 
 */
function submitInput(ke) {
  // send the result to the server if user pressed Enter
  // and the Username is valid
  if ((ke.key === "Enter") && !(document.getElementById('send_result'))) {

    document.getElementById('send_result').dispatchEvent(new Event('click'));
  }
}

function main() {
  redirectIfNotAllowed();

  showScore();
  showStats();
  setRetry();
  let stillToSend = (window.sessionStorage.getItem('success') === null);

  if (stillToSend) {
    setSend();
    //input validation and submit handlers
    addInputHandlers();

    //manually trigger validation after loading the page
    document.getElementById('username_input').dispatchEvent(new Event('input'));
  }
  else {

    updateSendResult(true);
  }
}

//start
main();