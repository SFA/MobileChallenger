<?php

    $mysqli = new mysqli("localhost", "mobilechallenger", "sparcmobile", "mobilechallenger");
    $user = $_POST['user'];
    $pass = $_POST['pass'];
    $preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser where username = ?");
    $preparedStatement->bind_param("s", $user);
    $preparedStatement->execute();

    $preparedStatement->bind_result($username, $password, $email, $first_name, $last_name);

    if ($preparedStatement->fetch()){
        if($username = $user && $password == $pass){
            print(rtrim("true"));
        } else {
            print(rtrim("false"));
        }
    }

    $preparedStatement->close();
    $mysqli->close();

?>
