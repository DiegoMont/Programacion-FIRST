<?php
//Buscar sesion activa
include "php/iniciar-sesion.php";

//Conectar a base de datos
include "php/conectar-DB.php";

$query = "SELECT lugar_regional, numero_equipo, numero_match, auto_upper, auto_bottom, teleop_upper, teleop_bottom, control_panel, shield_generator, vision, comentarios, created_at FROM equipos";
$result = mysqli_query($connection, $query);

$respuestas = mysqli_fetch_all($result, MYSQLI_ASSOC);
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
            <th rowspan="2">Control Panel</th>
            <th rowspan="2">Shield Generator</th>
            <th rowspan="2">Vision</th>
            <th rowspan="2" class="comentarios">Comentarios</th>
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
            echo "<td>" . htmlspecialchars($respuesta['control_panel']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['shield_generator']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['vision']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['comentarios']) . "</td>";
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
    <footer></footer>
  </body>
</html>
