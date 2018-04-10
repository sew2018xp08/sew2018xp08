<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $email = $_POST['email'];
    $password = $_POST['password'];

    //validation
    $sql = "SELECT * FROM user WHERE email = '$email' and password = '$password'";

    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        // output data of each row
        echo "succsess";
        
    } else {
        echo "WRONG INPUT";
    }

    $conn->close();
?>