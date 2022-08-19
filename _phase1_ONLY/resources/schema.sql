CREATE VIEW MaxScoreOfVersion AS
    SELECT us.quizuser AS quizuser, qu.nickname AS nickname, us.game_version AS game_version, MAX(us.total_score) AS score
    FROM userscore us
    INNER JOIN quizuser qu ON us.quizuser = qu.id_user
    GROUP BY us.quizuser, us.game_version, qu.nickname
    ORDER BY qu.nickname ASC, us.game_version DESC;

CREATE VIEW LeaderboardOfVersion AS 
    SELECT vms.game_version AS game_version, vms.score AS score, vms.nickname AS nickname, (SELECT MIN(us.score_timestamp) FROM userscore us WHERE vms.game_version=us.game_version AND vms.score=us.total_score AND vms.quizuser=us.quizuser) AS score_timestamp 
    FROM MaxScoreOfVersion vms 
    ORDER BY vms.game_version DESC, vms.score DESC, score_timestamp ASC;