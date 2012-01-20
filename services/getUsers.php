<?php
/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/20/12
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */

    $mysqli = new mysqli("localhost", "mobilechallenger", "sparcmobile", "mobilechallenger");
    $user = $_POST['user'];
    $pass = $_POST['pass'];
    $preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser");
    $preparedStatement->execute();

    $preparedStatement->bind_result($username, $password, $email, $first_name, $last_name);

    $user_array = array();
    while ($preparedStatement->fetch()){
        $user_array[] = array( "username" => $username, "password" => $password, "email" => $email, "fname" => $first_name, "lname" => $last_name);
    }
    print(json_encode(array("data" => $user_array)));

    $preparedStatement->close();
    $mysqli->close();

?>