<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $email = $_POST['email'];
    $password = $_POST['password'];

    $sql = 'SELECT u_id, first_name, last_name, email, admin FROM user WHERE email = ? and password = ?';

    $user_data = array();

    /* prepare statement */
    if ($stmt = $conn->prepare($sql))
    {
        $stmt->bind_param("ss", $email, $password);
        $stmt->execute();   
        $result = $stmt->get_result(); 

        if ($result)
        {
            if ($result->num_rows > 0)
            {
                while($row = $result->fetch_assoc())
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

        $stmt->close();
    }
    /* close connection */
    $conn->close();

?>