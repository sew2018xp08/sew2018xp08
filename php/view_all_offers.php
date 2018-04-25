<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    //validation
    $sql = 'SELECT o.o_id, o.title, u.first_name, u.last_name FROM offers o, user u WHERE o.u_id = u.u_id';

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