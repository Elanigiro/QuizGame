const ID_MAP = new Map([

  ['easy', 1],
  ['medium', 2],
  ['hard', 3],
  [1, 'easy'],
  [2, 'medium'],
  [3, 'hard'],
]);

const SCORES = ["100", "150", "200"];
const OPTIONS = ["2", "3", "4"];

/**
 * 
 * @param {HTMLButtonElement} b 
 */
function clickedButtonProcessing(b) {
  clearButtons();
  b.classList.add('selected');
  let selected = b.id;
  let selectedValue = ID_MAP.get(selected);

  let scoreElement = document.getElementById("score");
  let optionsElement = document.getElementById("options");

  scoreElement.textContent = SCORES[selectedValue - 1];
  optionsElement.textContent = OPTIONS[selectedValue - 1];
  scoreElement.classList = selected;
  optionsElement.classList = selected;

  window.localStorage.setItem('diff', selectedValue);
}

/**
 * 
 * @param {Event} e 
 */
function clickedButton(e) {

  clickedButtonProcessing(e.currentTarget);
}

function clearButtons() {
  document.querySelectorAll(".diff_button").forEach((el) => {

    el.classList = 'diff_button';
  });
}


function main(){

  let id = window.localStorage.getItem('diff');

  if (id) {

    clickedButtonProcessing(new HTMLButtonElement(document.getElementById(`${ID_MAP.get(id)}`)));
  }

  //set event handlers
  [...document.getElementsByClassName("diff_button")].forEach((el) => {

    el.onclick = clickedButton;
  });
}

//start
main();