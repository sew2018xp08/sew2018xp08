<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $title = 'test title';

    //validation
    $sql = "DELETE FROM offers WHERE title = '$title'";
    
    if ($conn->query($sql) === TRUE)
    {
        echo "test title is deleted";
    }
    else
    {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
?>