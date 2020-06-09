<?php
$connection = mysqli_connect('localhost', "diegod", 'dieguapo1234', 'bd_scouting');

if (!$connection) {
  $mensaje = "Error: " . mysqli_connect_error();
}
?>
