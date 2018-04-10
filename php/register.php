<?php

    include "PHP/config.php";

    $firstname = "John";
    $lastname = "Doe";
    $email = "johnn@example.com";
    $password = "1234";

    //Insert
    $sql = "INSERT INTO user (first_name, last_name, email, password)
    VALUES ('$firstname', '$lastname', '$email', '$password')";

    //check succsess
    if ($conn->query($sql) === TRUE) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
?>