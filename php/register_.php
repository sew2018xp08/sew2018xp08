<?php

     include "/users/sew2018xp08/www/PHP/config.php";

    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $pro = $_POST['pro'];

    //Insert
    $sql = "INSERT INTO user (first_name, last_name, email, password, pro)
    VALUES ('$firstname', '$lastname', '$email', '$password', '$pro')";

    //check succsess
    if ($conn->query($sql) === TRUE) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
?>