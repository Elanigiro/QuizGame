/**
 * @param {Event} e 
 */
 function clickedAnswer(e) {
    /** @type {Element}  */
    const selected = e.target;
  
    if (selected.classList.contains("quiz_button")){
  
      selected.classList.add("selected");
      //disable all buttons
      disableButtons();
      //add true/false class to buttons
      setButtonTruth();
    }
  }