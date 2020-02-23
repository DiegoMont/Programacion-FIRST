<?php
session_start(['cookie_lifetime' => 86400]);
if (isset($_SESSION["sesionIniciada"])) {
  if(!$_SESSION["sesionIniciada"] == 125) {
    header("Location: index.php");
    exit;
  }
}
?>
