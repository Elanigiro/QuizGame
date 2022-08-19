import 'dart:convert';
import 'dart:html';
import 'package:http/http.dart' as http;

import '../payload/UserScore.dart';
import '../utility/HttpHandlersManager.dart';

void main() async {
  await httpGetLeaderboard(Event("none"));
}

Future<void> httpGetLeaderboard(Event e) async {
  var records = await fetchJsonList(HttpHandlersManager.URL_LDRBOARD);

  displayRecords(records);
}

ParagraphElement buildLBRecord(int idx, String nickname, int score) {
  SpanElement td1 = SpanElement()..text = idx.toString();
  SpanElement td2 = SpanElement()..text = nickname;
  SpanElement td3 = SpanElement()..text = score.toString();

  return ParagraphElement()..children.addAll([td1, td2, td3]);
}

void displayRecords(List<UserScore> records) {
  DivElement tbody = querySelector("#leaderboard_records") as DivElement;

  int i = 1;
  for (UserScore entry in records) {
    tbody.children.add(buildLBRecord(i, entry.username, entry.result));
    ++i;
  }
}

Future<List<UserScore>> fetchJsonList(String url) async {
  final response = await http.get(Uri.parse(url));

  List<dynamic> json = jsonDecode(response.body);

  return List<UserScore>.from(json.map((e) => UserScore.fromJson(e)));
}
