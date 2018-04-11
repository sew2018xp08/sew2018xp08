<?php

    define('DB_USER', "sew2018xp08"); // db user
    define('DB_PASSWORD', "qwasyx12"); // db password (mention your db password here)
    define('DB_DATABASE', "sew2018xp08"); // database name
    define('DB_HOST', "localhost"); // db server

    $conn = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

    if (!$conn)
    {
      die('Could not connect: ' . mysqli_error());
    }

    $conn->query("SET NAMES 'utf8'");
?>