    <?php

    $mysqli = new mysqli("localhost", "mobilechallenger", "sparcmobile", "mobilechallenger");
    $user = $_REQUEST['user'];
    $pass = $_REQUEST['pass'];
    $email = $_REQUEST['email'];
    $fname = $_REQUEST['fname'];
    $lname = $_REQUEST['lname'];

    //$preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser where username = ?");
    $preparedStatement = $mysqli->prepare("INSERT INTO MOBILECHALLENGEUSER (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME)" +
        " VALUES (?, ?, ?, ?, ?)");
    //    $preparedStatement = $mysqli->prepare("SELECT * FROM mobilechallengeuser");
    $preparedStatement->bind_param("s", $user);
    $preparedStatement->bind_param("s", $pass);
    $preparedStatement->bind_param("s", $email);
    $preparedStatement->bind_param("s", $fname);
    $preparedStatement->bind_param("s", $lname);
    $preparedStatement->execute();

    $preparedStatement->close();
    $mysqli->close();

?>
