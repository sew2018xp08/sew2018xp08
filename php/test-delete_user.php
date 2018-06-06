<?php

    include "config.php";

    $email = 'test@test.com';

    //validation
    $sql = "DELETE FROM user WHERE email = '$email'";
    
    if ($conn->query($sql) === TRUE)
    {
        echo "test@test.com is deleted";
    }
    else
    {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
?>