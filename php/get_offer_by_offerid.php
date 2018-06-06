<?php
    include "config.php";

    $o_id = $_POST['offerID'];
    $sql = 'SELECT * FROM offers o, user u WHERE o.o_id = ? and o.u_id = u.u_id';
    $user_data = array();

    /* prepare statement */

    if ($stmt = $conn->prepare($sql))

    {

        $stmt->bind_param("i", $o_id);
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
            } else {
                echo "NO OFFERS HERE";
            }
        }
        $stmt->close();
    }
    $conn->close();
?>