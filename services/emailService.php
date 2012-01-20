<?php
    include 'emailTypes.php';
    require_once "Mail.php";

    $challenger = $_POST['challenger'];
    $challengee = $_POST['challengee'];

    createChallengeEmail($challenger, $challengee);

    $smtp = Mail::factory('smtp', array('host'=>$host, 'port'=>$port, 'auth'=>false, 'username'=>$username, 'password'=>$password));
    $mail = $smtp->send($email_to, $headers, $email_message);

    if (PEAR::isError($mail)) {
        echo("<p>" . $mail->getMessage() . "</p>");
    } else {
        echo("<p>Message successfully sent!</p>");
    }
?>