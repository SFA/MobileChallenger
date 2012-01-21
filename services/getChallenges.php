<?php
/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/20/12
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */

$mysqli = new mysqli("localhost", "mobilechallenger", "sparcmobile", "mobilechallenger");
$user = $_POST['username'];
$preparedStatement = $mysqli->prepare("SELECT * FROM challenge WHERE challengee = ? or challenger = ?");
$preparedStatement->bind_param("ss", $user, $user);
$preparedStatement->execute();

$preparedStatement->bind_result($id, $challengee, $challenger);

$user_array = array();
while ($preparedStatement->fetch()){
    $user_array[] = array( "id" => $id, "challengee" => $challengee, "challenger" => $challenger);
}
print(json_encode(array("data" => $user_array)));

$preparedStatement->close();
$mysqli->close();

?>