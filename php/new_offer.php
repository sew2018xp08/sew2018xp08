<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $u_id = $_POST['tutorID'];
    $title = $_POST['title'];
    $desc = $_POST['description'];

    //title + user exists already?
    $sql = "SELECT * FROM offers WHERE u_id = '$u_id' and title = '$title'";

    $result = $conn->query($sql);

    if ($result->num_rows > 0)
    {
        echo "Eintrag vom user ".$u_id." und titel ".$title." gibts schu";
        
    }
    else
    {
        //Insert
        $sql = "INSERT INTO offers (u_id, title, description)
        VALUES ('$u_id', '$title', '$desc')";

        //check succsess
        if ($conn->query($sql) === TRUE)
        {
            echo "New offer created successfully";
        }
        else
        {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
    }

    $conn->close();
?>