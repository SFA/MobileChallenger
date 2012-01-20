<?php
    require_once "Mail.php";

    $email_from = "johngyselinck@gmail.com";
    $email_to = "john.gyselinck@sparcedge.com";
    $email_subject = "Test Email";
    $email_message = "Test Email Message.";

    $headers = array('From' => $email_from, 'To'=>$email_to, 'Subject'=>$email_subject);

    $host = "localhost";
    $port = "25";
    $username = "john";
    $password = "pass";

    $smtp = Mail::factory('smtp', array('host'=>$host, 'port'=>$port, 'auth'=>false, 'username'=>$username, 'password'=>$password));
    $mail = $smtp->send($email_to, $headers, $email_message);

    if (PEAR::isError($mail)) {
        echo("<p>" . $mail->getMessage() . "</p>");
    } else {
        echo("<p>Message successfully sent!</p>");
    }
?>