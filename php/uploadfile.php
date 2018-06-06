<?php
if($_SERVER['REQUEST_METHOD']=='POST')
{
  // echo $_SERVER["DOCUMENT_ROOT"];  // /home1/demonuts/public_html
    include "config.php";

  //$_FILES['image']['name']   give original name from parameter where 'image' == parametername eg. city.jpg
  //$_FILES['image']['tmp_name']  temporary system generated name

  $user_id= $_POST['user_id'];

  $originalImgName= $_FILES['filename']['name'];
  $tempName= $_FILES['filename']['tmp_name'];
  $folder="ProfilePictures/";
  //update path as per your directory structure
  $url = "http://sew2018xp08.bplaced.net/ProfilePictures/".$originalImgName;

  if(move_uploaded_file($tempName,$folder.$originalImgName))
  {
    $query = "INSERT INTO user (profilePicture) VALUES ('$url') WHERE u_id = '$user_id'";
    if(mysqli_query($conn,$query))
    {
      $query= "SELECT * FROM user WHERE profilePicture='$url'";
      $result= mysqli_query($conn, $query);
      $emparray = array();
      if(mysqli_num_rows($result) > 0)
      {
        while ($row = mysqli_fetch_assoc($result))
        {
          $emparray[] = $row;
        }
        echo json_encode(array( "status" => "true","message" => "Successfully file added!" , "data" => $emparray) );
      }
      else
      {
        echo json_encode(array( "status" => "false","message" => "Failed!") );
      }
    }
    else
    {
      echo json_encode(array( "status" => "false","message" => "Failed!") );
    }
    //echo "moved to ".$url;
  }
  else
  {
    echo json_encode(array( "status" => "false","message" => "Failed!") );
  }
  $conn->close();
}
?>