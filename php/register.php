<?php

    include "config.php";

    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $pro = $_POST['pro'];
    //Insert
    $sql = 'INSERT INTO user (first_name, last_name, email, password, pro)
        VALUES (?, ?, ?, ?, ?)';

    /* prepare statement */
    if ($stmt = $conn->prepare($sql))
    {
        $stmt->bind_param("ssssi", $firstname, $lastname, $email, $password, $pro);

        if ($stmt->execute())
        {
            echo "New record created successfully";
        }
        else
        {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
        $stmt->close();
    }
    /* close connection */
    $conn->close();
?>