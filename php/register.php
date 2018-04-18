<?php

     include "/users/sew2018xp08/www/PHP/config.php";

    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    //Insert
    $sql = "INSERT INTO user (first_name, last_name, email, password)
    VALUES ('$firstname', '$lastname', '$email', '$password')";

    //check succsess
    if ($conn->query($sql) === TRUE)
    {
        echo "New record created successfully";
    }
    else
    {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
?>