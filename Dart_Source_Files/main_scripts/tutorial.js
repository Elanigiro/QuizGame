




void main() {
  //set eventListeners
  (querySelector("#A1") as ButtonElement).onClick.listen(clickedAlertAnswer);
  (querySelector("#A2") as ButtonElement).onClick.listen(clickedAlertAnswer);
  (querySelector("#A3") as ButtonElement).onClick.listen(clickedAlertAnswer);
  (querySelector("#A4") as ButtonElement).onClick.listen(clickedAlertAnswer);
}



void setButtonTruth() {
  (querySelector("#A1") as ButtonElement).classes.add("false");
  (querySelector("#A2") as ButtonElement).classes.add("true");
  (querySelector("#A3") as ButtonElement).classes.add("false");
  (querySelector("#A4") as ButtonElement).classes.add("false");
}
