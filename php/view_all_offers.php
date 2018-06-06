<?php

    include "config.php";


    //validation
    $sql = 'SELECT o.o_id, o.title, u.first_name, u.last_name, u.u_id, u.pro FROM offers o, user u WHERE o.u_id = u.u_id ORDER BY u.pro DESC, o.o_id ASC';

    $myArray = array();
    if ($result = $conn->query($sql))
    {
        while($row = $result->fetch_array(MYSQLI_ASSOC))
        {
                $myArray[] = $row;
        }
        echo json_encode($myArray, JSON_UNESCAPED_UNICODE);
    }

    $conn->close();
?>