<?php

    $mysqli = new mysqli("localhost", "mobilechallenger", "sparcmobile", "mobilechallenger");
    $user =  $_POST['user'];
    $pass =  $_POST['pass'];
    $email = $_POST['email'];
    $fname = $_POST['fname'];
    $lname = $_POST['lname'];

    //$preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser where username = ?");
    $preparedStatement = $mysqli->prepare("INSERT INTO MOBILECHALLENGEUSER (username, password, email, first_name, last_name) VALUES (?, ?, ?, ?, ?)");
    //    $preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser");
    $preparedStatement->bind_param("sssss", $user, $pass, $email, $fname, $lname);
    $preparedStatement->execute();
    $preparedStatement->close();
    $mysqli->close();

?>
