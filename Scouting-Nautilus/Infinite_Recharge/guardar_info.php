<?php
$regional;
$team_number;
$match;
$power_ports = [-1, -1, -1, -1];
$fouls;
$rotation_control;
$position_control;
$shield_generator;
$comentarios = "";
$moves_on_init;
$endgame;
$retrieve_cells;

//Buscar sesion activa
include "php/iniciar-sesion.php";

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
    $temp2 = filter_var(filter_var($temp2, FILTER_SANITIZE_NUMBER_INT), FILTER_VALIDATE_INT, ["options" => ["min_range" => 0, "max_range" => 99]]);
    if($temp2 >= 0)
      $power_ports[$i] = $temp2;
  }
  if($power_ports[$i] == -1) {
    header("Location: InfiniteRecharge.html");
    exit;
  }
}

//Validar campo fouls
if (isset($_POST["fouls"])) {
  $temp = htmlspecialchars($_POST["fouls"]);
  $temp = filter_var(filter_var($temp, FILTER_SANITIZE_NUMBER_INT), FILTER_VALIDATE_INT, ["options" => ["min_range" => 0, "max_range" => 50]]);
  if($temp >= 0)
    $fouls = $temp;
}
if (!isset($fouls)) {
  header("Location: InfiniteRecharge.html");
  exit;
}

//Validar campo rotation-control
if (isset($_POST["rotation-control"])) {
  $temp = htmlspecialchars($_POST["rotation-control"]);
  if (strlen($temp) == 1 && $temp === "1") {
    $rotation_control = "Activa";
  } else
    $rotation_control = "No aplica";
}
if(!isset($rotation_control)) {
  header("Location: InfiniteRecharge.html");
  exit;
}

//Validar campo position-control
if (isset($_POST["position-control"])) {
  $temp = htmlspecialchars($_POST["position-control"]);
  if (strlen($temp) == 1 && $temp === "1") {
    $position_control = "Activa";
  } else
    $position_control = "No aplica";
}
if(!isset($position_control)) {
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

//Preguntas adicionales
if(isset($_POST["moves"])){
  $temp = $_POST["moves"];
  if($temp === "1")
    $moves_on_init = "Se mueve";
  else
    $moves_on_init = "No se mueve";
} else $moves_on_init = "N / A";

if(isset($_POST["auto-retrieve"])){
  $temp = $_POST["auto-retrieve"];
  if($temp === "1")
    $retrieve_cells = "Recoge";
  else
    $retrieve_cells = "No recoge";
} else $retrieve_cells = "N / A";

if(isset($_POST["endgame"])){
  $temp = $_POST["endgame"];
  if($temp === "1")
    $endgame = "Estacionarse";
  else if($temp === "2")
    $endgame = "Colgarse";
  else
    $endgame = "Score Power Cells";
} else $endgame = "N / A";

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
        include "php/conectar-DB.php";

        //Guardar scouting en la base de datos
        $query = "INSERT INTO match_results(lugar_regional, numero_equipo, numero_match, auto_upper, auto_bottom, teleop_upper, teleop_bottom, fouls, rotation_control, position_control, shield_generator, comentarios, moves, auto_retrieve, endgame) VALUES ('$regional', '$team_number', '$match', '${power_ports[0]}', '${power_ports[1]}', '${power_ports[2]}', '${power_ports[3]}', '$fouls', '$rotation_control', '$position_control', '$shield_generator', '$comentarios', '$moves_on_init', '$retrieve_cells', '$endgame')";

        $result = mysqli_query($connection, $query);

        if (!$result) {
          $mensaje = "Error: Unable to save data";
          echo $result;
        }

        mysqli_close($connection);
        echo $mensaje;
        ?>
      </h1>
      <img src="img/gracias.jpg" alt="Exito" class="foto">
      <div class="flexbox">
        <a href="InfiniteRecharge.html" class="btn boton">Scouting match</a>
        <a href="resultados.php" class="btn boton">Estad&iacute;sticas</a>
        <a href="scouting-pit.html" class="btn boton">Scouting pit</a>
      </div>
    </div>
  </body>
</html>
