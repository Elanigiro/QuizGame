// ignore_for_file: constant_identifier_names

import 'dart:html';

const Map<String, String> ID_MAP = {
  "facile": "1",
  "normale": "2",
  "difficile": "3"
};

const List<String> TOPICS = [
  "10 history quotes",
  "10 movie quotes",
  "10 latin quotes"
];

void main() {
  // if the user previously made a choice for topic
  if (window.localStorage.containsKey("topic")) {
    var id = ID_MAP.entries
        .firstWhere((element) => element.value == window.localStorage["topic"]);

    clickedButtonProcessing(querySelector("#${id.key}") as ButtonElement);
  }

  //set event handlers
  querySelector("#facile")!.onClick.listen(clickedButton);
  querySelector("#normale")!.onClick.listen(clickedButton);
  querySelector("#difficile")!.onClick.listen(clickedButton);
}

void clickedButtonProcessing(ButtonElement b) {
  clearButtons();
  b.classes.add("selected");
  String selected = b.id;
  String selectedValue = ID_MAP[selected]!;

  querySelector("#description")!.text = TOPICS[int.parse(selectedValue) - 1];

  window.localStorage["topic"] = selectedValue;
}

void clickedButton(Event e) {
  ButtonElement sender = e.target as ButtonElement;
  clickedButtonProcessing(sender);
}

void clearButtons() {
  querySelectorAll("button").classes.clear();
}
