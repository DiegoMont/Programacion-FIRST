<?php
$regional;
$team_number;
$collector;
$power_cell_storage;
$drivetrain;
$generator_switch;
$switch_positions;
$carry;
$specialty;
$alianza;
$ports;
$vision;
$control_panel;
$position_control;
$fire_rate;
$weight;
$height;
$comentarios;

//Buscar sesion activa
include "php/iniciar-sesion.php";

include "php/validate_function.php";

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

//Validar campo nombre-equipo
if (isset($_POST["nombre-equipo"])) {
  $temp = htmlspecialchars($_POST["nombre-equipo"]);
  $temp = filter_var(filter_var($temp, FILTER_SANITIZE_NUMBER_INT), FILTER_VALIDATE_INT, ["options" => ["min_range" => 100, "max_range" => 99999]]);
  if ($temp)
    $team_number = $temp;
}

//Validar campo recolectores
if (isset($_POST["collectors"])) {
  $temp = htmlspecialchars($_POST["collectors"]);
  if(strlen($temp) == 1) {
    if($temp === "1")
      $collector = "Tiene recolector";
    else if($temp === "2")
      $collector = "No tiene";
  }
}

//Validar Power Cell storage
$temp = checarNumero($_POST["power-cell-storage"], 1, 5);
if($temp) {
  $power_cell_storage = $temp;
}

//Validar campo drivetrain
if (isset($_POST["drivetrain"])) {
  $temp = htmlspecialchars($_POST["drivetrain"]);
  if(strlen($temp) == 1) {
    if($temp === "1")
      $drivetrain = "Mecanum";
    else if($temp === "2")
      $drivetrain = "West Coast";
    else if($temp === "3")
      $drivetrain = "Omni";
    else if($temp === "4")
      $drivetrain = "High traction";
    else if($temp === "5") {
      $temp = htmlspecialchars($_POST["other-drivetrain"]);
      $drivetrain = substr(trim($temp), 0, 50);
    }
  }
}

//Validar campo shield-generator
if (isset($_POST["shield-generator"])) {
  $switch_positions = "No aplica";
  $carry = "No aplica";
  $temp = htmlspecialchars($_POST["shield-generator"]);
  if ($temp === "1") {
    $generator_switch = "Activa";

    //Posicion en que se cuelga
    if(isset($_POST["position-switch"])) {
      $temp = $_POST["position-switch"];
      if(strlen($temp) == 1) {
        switch ($temp) {
          case '1':
            $switch_positions = "Ambos lados";
            break;
          case '2':
            $switch_positions = "Centro";
            break;
          case '3':
            $switch_positions = "Cualquier lado";
            break;
        }
      }
    }

    //Carga otro robot?
    if(isset($_POST["carry"])) {
      $temp = htmlspecialchars($_POST["carry"]);
      if($temp === "1")
        $carry = "Carga otro robot";
      else if($temp === "2")
        $carry = "No carga";
    }
  } else {
    $generator_switch = "No";
  }
}

//Validar especializacion
$temp = validarTexto($_POST["specialty"]);
if(strlen($temp) > 4)
  $specialty = $temp;

//Validar alianza
$temp = validarTexto($_POST["needs-ally"]);
if(strlen($temp) > 4)
  $alianza = $temp;

//Validar campo port para anotar
if (isset($_POST["ports"])) {
  $temp = htmlspecialchars($_POST["ports"]);
  if(strlen($temp) == 1) {
    if($temp === "1")
      $ports = "Inner Port";
    else if($temp === "2")
      $ports = "Outer Port";
    else if($temp === "3")
      $ports = "Bottom Port";
  }
}

//Validar campo vision
if (isset($_POST["vision-enabled"])) {
  $temp = htmlspecialchars($_POST["vision-enabled"]);
  if(strlen($temp) == 1) {
    if($temp === "1")
      $vision = "Usa vision";
    else
      $vision = "No tiene";
  }
}

//Validar campo recolectores
if (isset($_POST["control-panel"])) {
  $position_control = "No aplica";
  $temp = htmlspecialchars($_POST["control-panel"]);
  if(strlen($temp) == 1) {
    if($temp === "1"){
      $control_panel = "Activa";
      if (isset($_POST["position-control"])) {
        $temp = htmlspecialchars($_POST["position-control"]);
        if($temp === "1")
          $position_control = "Position control";
        else if($temp === "2")
          $position_control = "No puede";
      }
    }else if($temp === "2")
      $control_panel = "No cumple";
  }
}

//Validar cadencia
$temp = validarDouble($_POST["fire-rate"], 0.01, 300);
if($temp)
  $fire_rate = $temp;

//Validar peso
$temp = validarDouble($_POST["weight"], 1, 125);
if($temp)
  $weight = $temp;

//Validar altura
$temp = validarDouble($_POST["height"], 1, 45);
if($temp)
  $height = $temp;

//Validar comentarios
if(isset($_POST["comentarios"])) {
  $temp = trim(htmlspecialchars($_POST["comentarios"]));
  $comentarios = substr($temp, 0, 255);
}

//Regresar en caso de falla
if(!isset($regional) || !isset($team_number) || !isset($collector) || !isset($power_cell_storage) || !isset($drivetrain) || !isset($generator_switch) || !isset($switch_positions) || !isset($carry) || !isset($specialty) || !isset($alianza) || !isset($ports) || !isset($vision) || !isset($control_panel) || !isset($position_control) || !isset($fire_rate) || !isset($weight) || !isset($height) || !isset($comentarios)) {
  header("Location: scouting-pit.html");
  exit;
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
        $query = "INSERT INTO pit_results(lugar_regional, numero_equipo, collector, store_cells, drivetrain, shield_generator, switch_positions, carry_robot, specialty, needs_in_allies, target_ports, vision_enabled, control_panel, position_control, fire_rate, weight, height, comments) VALUES('$regional', '$team_number', '$collector', '$power_cell_storage', '$drivetrain', '$generator_switch', '$switch_positions', '$carry', '$specialty', '$alianza', '$ports', '$vision', '$control_panel', '$position_control', '$fire_rate', '$weight', '$height', '$comentarios')";

        $result = mysqli_query($connection, $query);

        if (!$result) {
          $mensaje = "Error: Unable to save data";
          echo $result;
        }

        mysqli_close($connection);
        echo $mensaje;
        ?>
      </h1>
      <img src="img/bb8.jpg" alt="Exito" class="foto">
      <div class="flexbox">
        <a href="InfiniteRecharge.html" class="btn boton">Scouting match</a>
        <a href="resultados.php" class="btn boton">Estad&iacute;sticas</a>
        <a href="scouting-pit.html" class="btn boton">Scouting pit</a>
      </div>
    </div>
  </body>
</html>
