<?php
//Buscar sesion activa
include "php/iniciar-sesion.php";

//Conectar a base de datos
include "php/conectar-DB.php";

$query = "SELECT lugar_regional, numero_equipo, numero_match, auto_upper, auto_bottom, teleop_upper, teleop_bottom, fouls, rotation_control, position_control, shield_generator, comentarios, created_at FROM match_results";
$result = mysqli_query($connection, $query);

$respuestas = mysqli_fetch_all($result, MYSQLI_ASSOC);

$query2 = "SELECT lugar_regional, numero_equipo, collector, store_cells, drivetrain, shield_generator, switch_positions, carry_robot, specialty, needs_in_allies, target_ports, vision_enabled, control_panel, position_control, fire_rate, weight, height, comments, created_at FROM pit_results";
$result2 = mysqli_query($connection, $query2);

$respuestas2 = mysqli_fetch_all($result2, MYSQLI_ASSOC);
#print_r($respuestas2);
#print_r($respuestas);

mysqli_close($connection);
?>
<html lang="es" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultados | Scouting</title>
    <link rel="stylesheet" type="text/css" href="css/Reset.css">
    <link rel="stylesheet" type="text/css" href="css/general.css">
    <link rel="stylesheet" type="text/css" href="css/estilo-resultados.css">
    <link rel="icon" type="image/png" href="">
    <!--<script src="js/interactividad.js" charset="utf-8" defer></script>-->
  </head>
  <body>
    <header>
      <div class="centre">
        <img src="img/nautilus.png" alt="Nautilus" id="logo">
        <h3>Scouting</h3>
        <div class="flexbox">
          <button type="button" class="active btn-display">Tabla</button>
        </div>
      </div>
    </header>
    <div class="seccion flexbox" id="">
      <div class="tabla">
      <table>
        <thead>
          <tr>
            <th rowspan="2">Equipo</th>
            <th colspan="2">Autonomous</th>
            <th colspan="2">Teleop</th>
            <th rowspan="2">Rotation Control</th>
            <th rowspan="2">Position Control</th>
            <th rowspan="2">Shield Generator</th>
            <th rowspan="2" class="comentarios">Comentarios</th>
            <th rowspan="2">Fouls</th>
            <th rowspan="2">Regional</th>
            <th rowspan="2">Match</th>
            <th rowspan="2" class="marca-temporal">Marca Temporal</th>
          </tr>
          <tr>
            <th>Outer/ Inner Port</th>
            <th>Bottom Port</th>
            <th>Outer/ Inner Port</th>
            <th>Bottom Port</th>
          </tr>
        </thead>
        <tbody>
          <?php
          foreach ($respuestas as $respuesta) {
            echo "<tr>";
            echo "<td>" . htmlspecialchars($respuesta['numero_equipo']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['auto_upper']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['auto_bottom']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['teleop_upper']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['teleop_bottom']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['rotation_control']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['position_control']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['shield_generator']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['comentarios']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['fouls']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['lugar_regional']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['numero_match']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['created_at']) . "</td>";
            echo "</tr>";
          }
          ?>
        </tbody>
      </table>
      </div>
    </div>

    <div class="seccion flexbox" id="">
      <div class="tabla">
      <table>
        <thead>
          <tr>
            <th rowspan="2">Equipo</th>
            <th rowspan="2">Recolector</th>
            <th rowspan="2">Almacenamiento Power Cells</th>
            <th rowspan="2">Drivetrain</th>
            <th colspan="3">Shield Generator</th>
            <th rowspan="2">Target Ports</th>
            <th rowspan="2">Cadencia (s)</th>
            <th colspan="2">Control Panel</th>
            <th rowspan="2" class="comentarios">En que se especializa?</th>
            <th rowspan="2" class="comentarios">Aliados</th>
            <th rowspan="2">Peso (lbs)</th>
            <th rowspan="2">Altura (in)</th>
            <th rowspan="2">Regional</th>
            <th rowspan="2" class="marca-temporal">Marca Temporal</th>
          </tr>
          <tr>
            <th>Se cuelga?</th>
            <th>Posiciones</th>
            <th>Puede cargar?</th>
            <th>Rotation Control</th>
            <th>Position Control</th>
          </tr>
        </thead>
        <tbody>
          <?php
          foreach ($respuestas2 as $respuesta) {
            echo "<tr>";
            echo "<td>" . htmlspecialchars($respuesta['numero_equipo']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['collector']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['store_cells']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['drivetrain']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['shield_generator']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['switch_positions']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['carry_robot']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['target_ports']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['fire_rate']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['control_panel']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['position_control']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['specialty']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['needs_in_allies']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['weight']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['height']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['lugar_regional']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['created_at']) . "</td>";
            echo "</tr>";
          }
          ?>
        </tbody>
      </table>
      </div>
    </div>

    <footer>
      <div class="centre firma flexbox">
        <a href="https://github.com/DiegoMont" class="firma">&lt;/&gt; with <span id="corazon" class="firma">&#9829;</span> by <span class="firma">DiegoMont</span></a>
      </div>
    </footer>
  </body>
</html>
