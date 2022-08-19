// ignore_for_file: file_names

class UserScore {
  String username;
  int result;
  int? difficulty;
  int? topic;
  int? gameVersion;

  UserScore(
      {required this.username,
      required this.result,
      this.difficulty,
      this.topic,
      this.gameVersion});

  factory UserScore.fromJson(Map<String, dynamic> json) {
    return UserScore(username: json["nickname"], result: json["score"]);
  }

  Map<String, dynamic> toJson() {
    return {
      "username": username,
      "result": result,
      "difficulty": difficulty,
      "topic": topic,
      "gameVersion": gameVersion
    };
  }
}
