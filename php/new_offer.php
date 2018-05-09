<?php

include "/users/sew2018xp08/www/PHP/config.php";

$u_id = $_POST['tutorID'];
$title = $_POST['title'];
$desc = $_POST['description'];
$price = $_POST['price'];


//title + user exists already?
$sql = 'SELECT * FROM offers WHERE u_id = ? and title = ?';

/* prepare statement */
if ($stmt = $conn->prepare($sql))
{
    $stmt->bind_param("is", $u_id, $title);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result)
    {
        if ($result->num_rows > 0)
        {
            echo "Eintrag vom user ".$u_id." und titel ".$title." gibts schu";
        }
        else
        {
            //Insert
            $sql2 = 'INSERT INTO offers (u_id, title, description, price)
                      VALUES (?, ?, ?, ?)';

            /* prepare statement */
            if ($stmt2 = $conn->prepare($sql2))
            {
                $stmt2->bind_param("iss", $u_id, $title, $desc, $price);

                if ($stmt2->execute())
                {
                    echo "New offer created successfully";
                }
                else
                {
                    echo "Error: " . $sql2 . "<br>" . $conn->error;
                }
                $stmt2->close();
            }
        }
    }
    $stmt->close();
}
$conn->close();
?>