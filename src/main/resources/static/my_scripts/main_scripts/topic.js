const ID_MAP = new Map([

  ['history', 1],
  ['movies', 2],
  ['latin', 3],
  [1, 'history'],
  [2, 'movies'],
  [3, 'latin'],
]);

const TOPICS = [
  "10 history quotes",
  "10 movie quotes",
  "10 latin quotes",
];

/**
 * 
 * @param {HTMLButtonElement} b 
 */
function clickedButtonProcessing(b) {
  clearButtons();
  b.classList.add('selected');
  let selected = b.id;
  let selectedValue = ID_MAP.get(selected);

  document.getElementById("description").textContent = TOPICS[selectedValue - 1];

  window.localStorage.setItem('topic', selectedValue);
}

function clearButtons() {
  document.querySelectorAll(".topic_button").forEach((el) => {

    el.classList = 'topic_button';
  });
}

/**
 * 
 * @param {Event} e 
 */
 function clickedButton(e) {

  clickedButtonProcessing(e.currentTarget);
}

function main(){

  let id = window.localStorage.getItem('topic');

  if (id) {

    clickedButtonProcessing(document.getElementById(`${ID_MAP.get(parseInt(id))}`));
  }

  //set event handlers
  [...document.getElementsByClassName("topic_button")].forEach((el) => {

    el.onclick = clickedButton;
  });
}

//start
main();