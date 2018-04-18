<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $u_id = "1";

    //validation
    $sql = "SELECT * FROM offers WHERE u_id = '$u_id'";

    





    $myArray = array();
    if ($result = $conn->query($sql)) {

        while($row = $result->fetch_array(MYSQL_ASSOC)) {
                $myArray[] = $row;
        }
        echo json_encode($myArray);
    }

    $arr = get_defined_vars();
print_r($arr);


   /* 
$result = $conn->query($sql);
   if ($result->num_rows > 0) {
        
        $mysqli = new mysqli('localhost','user','password','myDatabaseName');
        

        echo "succsess";
        while($row = $result->fetch_assoc()) {
            echo "id: " . $row["o_id"]. " - title: " . $row["title"]. " " . $row["description"]. "<br>";
        }
        
    } else {
        echo "nothing here";
    }*/

    $conn->close();
?>