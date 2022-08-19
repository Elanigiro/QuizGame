// ignore_for_file: constant_identifier_names

import 'dart:html';

const Map<String, String> ID_MAP = {
  "facile": "1",
  "normale": "2",
  "difficile": "3"
};

const List<String> SCORES = ["100", "150", "200"];
const List<String> OPTIONS = ["2", "3", "4"];

void main() {
  if (window.localStorage.containsKey("diff")) {
    var id = ID_MAP.entries
        .firstWhere((element) => element.value == window.localStorage["diff"]);

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

  querySelector("#score")!
    ..text = SCORES[int.parse(selectedValue) - 1]
    ..classes.clear();
  querySelector("#options")!
    ..text = OPTIONS[int.parse(selectedValue) - 1]
    ..classes.clear();

  querySelector("#score")!.classes.add(selected);
  querySelector("#options")!.classes.add(selected);

  window.localStorage["diff"] = selectedValue;
}

void clickedButton(Event e) {
  ButtonElement sender = e.target as ButtonElement;
  clickedButtonProcessing(sender);
}

void clearButtons() {
  querySelectorAll("button").classes.clear();
}
