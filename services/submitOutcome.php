<?php
/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/20/12
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */

$mysqli = new mysqli("localhost", "mobilechallenger", "sparcmobile", "mobilechallenger");
$challenger = $_REQUEST['challenger'];
$challengee = $_REQUEST['challengee'];

//$preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser where username = ?");
$preparedStatement = $mysqli->prepare("INSERT INTO Outcome (challengee, challenger) VALUES (?, ?)");
//    $preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser");
$preparedStatement->bind_param("ss", $challengee, $challenger);
$preparedStatement->execute();

$preparedStatement->close();
$mysqli->close();

?>