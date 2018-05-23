<?php

include "/users/sew2018xp08/www/PHP/config.php";

$offer_id = $_POST['o_id'];

//Insert
$sql = "DELETE FROM offers WHERE o_id = ?";

/* prepare statement */
if ($stmt = $conn->prepare($sql))
{
    $stmt->bind_param("i", $offer_id);

    if ($stmt->execute())
    {
        echo "false";
        //echo "Offer Number ".$offer_id." is deleted";
    }
    else
    {
        echo "true";
        //echo "Error: " . $sql . "<br>" . $conn->error;
    }
    $stmt->close();
}

$conn->close();
?>