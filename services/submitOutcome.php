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

//Create the match entry and then get the id of the created match
$matchInsert = "insert into matches (challenge_id) values ($challenge_id)";
$matchInsertQueryResult = mysql_query($matchInsert);
echo("MIResult: ".$matchInsertQueryResult);
$matchQuery = "select id from matches where challenge_id = $challenge_id";
$matchQueryResult = mysql_query($matchQuery);
echo ("MQResult: ".print_r($matchQueryResult));
if(mysql_num_rows($matchQueryResult) > 1){
    echo "TOO MANY ROWS!!!";
}
while($row = mysql_fetch_assoc($matchQueryResult)){
   $matchId = $row['id'];
}

//Create the game entry and then get the id of the created game
$gameInsert = "insert into game (match_id) values ($matchId)";
$gameInsertQueryResult = mysql_query($gameInsert);
echo("GIResult: ".$gameInsertQueryResult);
$gameQuery = "select id from game where match_id = $matchId";
$gameQueryResult = mysql_query($gameQuery);
echo ("GQResult: ".$gameQueryResult);
if(mysql_num_rows($gameQueryResult) > 1){
    echo "TOO MANY ROWS!!!";
}
while($row = mysql_fetch_assoc($gameQueryResult)){
    $gameId = $row['id'];
}

//Create the outcome entry for the game
$insertQuery = "INSERT INTO outcome (game_id, challenger_score, challengee_score) VALUES ('$gameId', '$challenger_score', '$challengee_score')";
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