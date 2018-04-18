<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    //validation
    $sql = "SELECT * FROM offers";

    $myArray = array();
    if ($result = $conn->query($sql))
    {
        while($row = $result->fetch_array(MYSQL_ASSOC))
        {
                $myArray[] = $row;
        }
        echo json_encode($myArray, JSON_UNESCAPED_UNICODE);
    }

    $conn->close();
?>