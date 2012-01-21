<?php
/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/20/12
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */


mysql_connect("localhost", "mobilechallenger", "sparcmobile");
mysql_select_db("mobilechallenger");
$challenge_id = $_POST['challenge_id'];
$challenger_score = $_POST['challenger_score'];
$challengee_score = $_POST['challengee_score'];

$matchQuery = "select id from matches where challenge_id = $challenge_id";
$matchQueryResult = mysql_query($matchQuery);
echo ("MQResult: ".$matchQueryResult);
$gameQuery = "select id from game where match_id = $matchQueryResult";
$gameQueryResult = mysql_query($gameQuery);
echo ("GQResult: ".$gameQueryResult);
$insertQuery = "INSERT INTO outcome (game_id, challenger_score, challengee_score) VALUES ('$challenge_id', '$challenger_score', '$challengee_score')";
echo ("INSERT: ".$insertQuery);
$result = mysql_query($insertQuery);
if(!$result){
    die('Invalid query: '.mysql_error());
}
//$preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser where username = ?");
//$preparedStatement = $mysqli->prepare("INSERT INTO Outcome (game_id, challenger_score, challengee_score) VALUES (?, ?, ?)");
//    $preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser");
//$preparedStatement->bind_param("sss", $challenge_id, $challenger_score, $challengee_score);
//$preparedStatement->execute();

//$preparedStatement->close();
//$mysqli->close();

?>