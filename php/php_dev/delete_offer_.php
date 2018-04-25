<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $offer_id = $_POST['o_id'];


    //validation
    $sql = "DELETE FROM offers WHERE o_id = '$offer_id'";
    
    if ($conn->query($sql) === TRUE) {
        echo "Offer Number ".$offer_id." is deleted";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
?>