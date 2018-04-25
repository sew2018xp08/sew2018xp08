<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $u_id = $_POST['tutorID'];;

    //validation
    $sql = "SELECT * FROM offers WHERE u_id = '$u_id'";


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