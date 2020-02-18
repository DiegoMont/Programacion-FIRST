<?php
$regional;
$team_number;
$match;
$power_ports = [-1, -1, -1, -1];
$control_panel;
$shield_generator;
$vision_enabled;
$comentarios = "";

//Buscar sesion activa
session_start();
if (!isset($_SESSION["sesionIniciada"]) && !$_SESSION["sesionIniciada"] == 125) {
  header("Location: index.php");
  exit;
}

//Validar campo zona-regional
if(isset($_POST["zona-regional"])){
  $temp = htmlspecialchars($_POST["zona-regional"]);
  if (strlen(trim($temp)) < 30) {
    $temp = strtolower($temp);
    if ($temp === "monterrey") {
      $regional = "Monterrey";
    } else if($temp === "sacramento")
      $regional = "Sacramento";
  }
}
if(!isset($regional)) {
  header("Location: InfiniteRecharge.html");
  exit;
}

//Validar campo nombre-equipo
if (isset($_POST["nombre-equipo"])) {
  $temp = htmlspecialchars($_POST["nombre-equipo"]);
  $temp = filter_var(filter_var($temp, FILTER_SANITIZE_NUMBER_INT), FILTER_VALIDATE_INT, ["options" => ["min_range" => 100, "max_range" => 99999]]);
  if ($temp)
    $team_number = $temp;
}
if(!isset($team_number)) {
  header("Location: InfiniteRecharge.html");
  exit;
}

//Validar campo numero-match
if (isset($_POST["numero-match"])) {
  $temp = htmlspecialchars($_POST["numero-match"]);
  $temp = filter_var(filter_var($temp, FILTER_SANITIZE_NUMBER_INT), FILTER_VALIDATE_INT, ["options" => ["min_range" => 1, "max_range" => 150]]);
  if($temp)
    $match = $temp;
}
if(!isset($match)) {
  header("Location: InfiniteRecharge.html");
  exit;
}


//Validar power ports
$temp = ["autonomous-outer", "autonomous-bottom", "teleop-outer", "teleop-bottom"];
for ($i = 0; $i < count($power_ports); $i++) {
  if (isset($_POST[$temp[$i]])) {
    $temp2 = htmlspecialchars($_POST[$temp[$i]]);
    $temp2 = filter_var(filter_var($temp2, FILTER_SANITIZE_NUMBER_INT), FILTER_VALIDATE_INT, ["options" => ["min_range" => -1, "max_range" => 99]]);
    if($temp2 >= 0)
      $power_ports[$i] = $temp2;
  }
  if($power_ports[$i] == -1) {
    header("Location: InfiniteRecharge.html");
    exit;
  }
}

//Validar campo rotation-control
if (isset($_POST["rotation-control"])) {
  $temp = htmlspecialchars($_POST["rotation-control"]);
  if (strlen($temp) == 1 && $temp === "1") {
    $control_panel = "Activa";
  } else
    $control_panel = "No aplica";
}
if(!isset($control_panel)) {
  header("Location: InfiniteRecharge.html");
  exit;
}

//Validar campo shield-generator
if (isset($_POST["shield-generator"])) {
  $temp = htmlspecialchars($_POST["shield-generator"]);
  if (strlen($temp) == 1 && $temp === "1") {
    $shield_generator = "Activa";
  } else {
    $shield_generator = "No aplica";
  }
}
if(!isset($shield_generator)) {
  header("Location: InfiniteRecharge.html");
  exit;
}

//Validar campo vision-enabled
if (isset($_POST["vision-enabled"])) {
  $temp = htmlspecialchars($_POST["vision-enabled"]);
  if (strlen($temp) == 1 && $temp === "1") {
    $vision_enabled = "Vision";
  } else {
    $vision_enabled = "No vision";
  }
}
if(!isset($vision_enabled)) {
  header("Location: InfiniteRecharge.html");
  exit;
}

//Validar comentarios
if(isset($_POST["comentarios"])) {
  $temp = htmlspecialchars($_POST["comentarios"]);
  $comentarios = substr($temp, 0, 255);
}
?>

<html lang="es" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Scouting</title>
    <link rel="stylesheet" type="text/css" href="css/Reset.css">
    <link rel="stylesheet" type="text/css" href="css/general.css">
    <style media="screen">
      .foto {
        width: 90%;
        max-width: 25rem;
        display: block;
        margin: 2rem auto;
      }

      .boton {
        color: #000;
        background-color: #f3e216;
        width: 25%;
        max-width: 10rem;
        min-width: 110px;
        padding: 0.75rem 0;
        border: 2px solid #f3e216;
        border-radius: 5px;
      }

      .boton:hover {
        color: #f3e216;
        background-color: #000;
      }
    </style>
  </head>
  <body>
    <div class="centre">
      <h1 class="light-font">
        <?php
        $mensaje = "Se ha guardado exitosamente. Gracias!";

        //Conectar a base de datos
        $connection = mysqli_connect('localhost', "diegod", 'dieguapo1234', 'bd_scouting');

        if (!$connection) {
          $mensaje = "Error: " . mysqli_connect_error();
        }

        //Guardar scouting en la base de datos
        $query = "INSERT INTO equipos(lugar_regional, numero_equipo, numero_match, auto_upper, auto_bottom, teleop_upper, teleop_bottom, control_panel, shield_generator, vision, comentarios) VALUES ('$regional', '$team_number', '$match', '${power_ports[0]}', '${power_ports[1]}', '${power_ports[2]}', '${power_ports[3]}', '$control_panel', '$shield_generator', '$vision_enabled', '$comentarios')";

        $result = mysqli_query($connection, $query);

        if (!$result) {
          $mensaje = "Error: Unable to save data";
        }

        mysqli_close($connection);
        echo $mensaje;
        ?>
      </h1>
      <img src="img/bb8.jpg" alt="Exito" class="foto">
      <div class="flexbox">
        <a href="index.php" class="btn boton">Scouting nuevo</a>
        <a href="resultados/index.php" class="btn boton">Estad&iacute;sticas</a>
      </div>
    </div>
  </body>
</html>
