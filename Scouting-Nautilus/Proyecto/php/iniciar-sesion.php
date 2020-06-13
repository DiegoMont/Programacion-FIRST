<?php
session_start(['cookie_lifetime' => 86400]);
if (!isset($_SESSION["sesion"]) || $_SESSION["sesion"] !== "inicializada"){
  header("Location:index.php");
  exit;
}