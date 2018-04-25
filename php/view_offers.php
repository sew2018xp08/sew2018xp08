<?php

    include "/users/sew2018xp08/www/PHP/config.php";

    $u_id = $_POST['tutorID'];;

    $sql = 'SELECT * FROM offers WHERE u_id = ?';
    $user_data = array();


    /* prepare statement */
    if ($stmt = $conn->prepare($sql))
    {
        $stmt->bind_param("i", $u_id);
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
                echo "NO OFFERS HERE";
            }
        }

        $stmt->close();
    }
    $conn->close();
?>