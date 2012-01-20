<?php

    $email_from;// = "johngyselinck@gmail.com";
    $email_to;// = "john.gyselinck@sparcedge.com";
    $email_subject;
    $email_message;

    $challenger_uname;
    $challenger_email;
    $challenger_fname;
    $challenger_lname;

    $challengee_uname;
    $challengee_email;
    $challengee_fname;
    $challengee_lname;

    $headers = array();// = array('From' => $email_from, 'To'=>$email_to, 'Subject'=>$email_subject);

    $host = "localhost";
    $port = "25";
    $username = "john";
    $password = "pass";

    function createChallengeEmail($challenger, $challengee){
        global $email_subject, $email_message, $headers, $email_from, $email_to, $challenger_uname, $challenger_email, $challenger_fname, $challenger_lname, $challengee_email, $challengee_fname, $challengee_lname;
        populateChallenger($challenger);
        populateChallengee($challengee);
        $email_to = $challengee_email;
        $headers['From'] = "From:$challenger_email";
        $headers['To'] = "To:$challengee_email";
        $headers['Subject'] = "You Have Received a SPARC Mobile Challenge";
        $email_message = "$challengee_fname $challengee_lname: You have received a SPARC Mobile Challenge from $challenger ($challenger_fname $challenger_lname)";
    }

    function populateChallenger($challenger){
        global $challenger_uname, $challenger_email, $challenger_fname, $challenger_lname;
        $mysqli = new mysqli("localhost", "mobilechallenger", "sparcmobile", "mobilechallenger");
        $preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser where username = ?");
        $preparedStatement->bind_param("s", $challenger);
        $preparedStatement->execute();

        $preparedStatement->bind_result($username, $password, $email, $first_name, $last_name);

        if ($preparedStatement->fetch()){
            $challenger_email = $email;
            $challenger_fname = $first_name;
            $challenger_lname = $last_name;
        }

        $preparedStatement->close();
        $mysqli->close();
    }

    function populateChallengee($challengee){
        global $challengee_uname, $challengee_email, $challengee_fname, $challengee_lname;
        $mysqli = new mysqli("localhost", "mobilechallenger", "sparcmobile", "mobilechallenger");
        $preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser where username = ?");
        $preparedStatement->bind_param("s", $challengee);
        $preparedStatement->execute();

        $preparedStatement->bind_result($username, $password, $email, $first_name, $last_name);

        if ($preparedStatement->fetch()){
            $challengee_email = $email;
            $challengee_fname = $first_name;
            $challengee_lname = $last_name;
        }

        $preparedStatement->close();
        $mysqli->close();
    }
?>