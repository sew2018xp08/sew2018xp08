<?php
  if($_SERVER['REQUEST_METHOD']=='POST')
  {
    // echo $_SERVER["DOCUMENT_ROOT"];  // /home1/demonuts/public_html
    include "config.php";

    $user_id = $_POST['tutorID'];
    $image = $_POST['picture'];

    $sql = "UPDATE user SET profilePicture = ? WHERE u_id = ?"; 
   
    /* prepare statement */
    if ($stmt = $conn->prepare($sql))
    {
        $stmt->bind_param("si", $image, $user_id);

        if ($stmt->execute())
        {
            echo "Image Uploaded Successfully";
        }
        else
        {
          echo "Error Uploading Image";
        }
        $stmt->close();
    }
    else
    {
      echo "prepare error";
    }
    /* close connection */
    $conn->close();

  }
?>