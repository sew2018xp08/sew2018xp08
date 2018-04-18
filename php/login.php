<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $email = $_POST['email'];
    $password = $_POST['password'];

    //validation
    $sql = "SELECT u_id, first_name, last_name, email, admin FROM user WHERE email = '$email' and password = '$password'";

    $result = $conn->query($sql);
    $user_data = array();

    if ($result)
    {
        if ($result->num_rows > 0)
        {
            while($row = $result->fetch_array(MYSQL_ASSOC))
            {
                $user_data[] = $row;
            }
            echo json_encode($user_data, JSON_UNESCAPED_UNICODE);
        }
        else
        {
           echo "WRONG INPUT";
        }
    }
    $conn->close();
?>