<?php
//Buscar sesion activa
include "php/iniciar-sesion.php";

//Conectar a base de datos
include "php/conectar-DB.php";

$query = "SELECT lugar_regional, numero_equipo, numero_match, auto_upper, auto_bottom, teleop_upper, teleop_bottom, fouls, rotation_control, position_control, shield_generator, comentarios, created_at, moves, auto_retrieve, endgame FROM match_results";
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
    <script src="js/interactividad-resultados.js" charset="utf-8" defer></script>
  </head>
  <body>
    <header>
      <div class="centre">
        <a href="index.php">
          <img src="img/nautilus.png" alt="Nautilus" id="logo">
          <h3>Scouting</h3>
        </a>
        <div class="flexbox">
          <button type="button" class="active btn-display" id="boton-tablas">Tabla</button>
          <button type="button" class="btn-display" id="boton-teams">Equipos</button>
        </div>
      </div>
    </header>
    <div class="seccion flexbox tablitas">
      <div class="tabla">
      <table>
        <thead>
          <tr>
            <th rowspan="2">Equipo</th>
            <th colspan="2">Autonomous</th>
            <th rowspan="2">Recoge Power Cells</th>
            <th rowspan="2">Sale de la Initiation Line</th>
            <th colspan="2">Teleop</th>
            <th rowspan="2">Rotation Control</th>
            <th rowspan="2">Position Control</th>
            <th rowspan="2">Shield Generator</th>
            <th rowspan="2">Enfoque durante Endgame</th>
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
            echo "<td>" . htmlspecialchars($respuesta['auto_retrieve']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['moves']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['teleop_upper']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['teleop_bottom']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['rotation_control']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['position_control']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['shield_generator']) . "</td>";
            echo "<td>" . htmlspecialchars($respuesta['endgame']) . "</td>";
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

    <div class="seccion flexbox tablitas">
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

    <div class="seccion teams">
      <?php
      $nombres = ["4010"=> "Nautilus", "3933"=> "Taman Ket"];
      $resumen_equipos;
      foreach ($respuestas2 as $index) {
        #print_r($index);
        $arreglo_equipo["numero"] = $index["numero_equipo"];
        $arreglo_equipo["nombre"] = isset($nombres[$arreglo_equipo["numero"]]) ? $nombres[$arreglo_equipo["numero"]]: "";
        $arreglo_equipo["carga"] = $index["store_cells"];
        $arreglo_equipo["cadencia"] = $index["fire_rate"];
        $arreglo_equipo["switch"] = "img/" . ($index["shield_generator"] === "No"? "robot-roto": "shield-generator") . ".jpg";
        $arreglo_equipo["peso"] = $index["weight"];
        $arreglo_equipo["altura"] = $index["height"];
        $nombre_imagen;
        switch ($index["drivetrain"]) {
          case 'West Coast':
            $nombre_imagen = "West_Coast";
            $arreglo_equipo["buscar"] = true;
            break;
          case 'High traction':
            $nombre_imagen = "High_Traction";
            $arreglo_equipo["buscar"] = true;
            break;
          case 'Omni':
            $nombre_imagen = "Omni";
            $arreglo_equipo["buscar"] = true;
            break;
          case 'Mecanum':
            $nombre_imagen = "Mecanum";
            $arreglo_equipo["buscar"] = true;
            break;
          default:
            $arreglo_equipo["buscar"] = false;
            break;
        }
        if ($arreglo_equipo["buscar"]) {
          $arreglo_equipo["chasis"] = "img/" . $nombre_imagen . ".jpg";
        }

        //iterar por todos los scouting match
        $contador = 0;
        $arreglo_equipo["avg_auto_up"] = 0;
        $arreglo_equipo["avg_auto_bot"] = 0;
        $arreglo_equipo["avg_tele_up"] = 0;
        $arreglo_equipo["avg_tele_bot"] = 0;
        $arreglo_equipo["high_auto_up"] = 0;
        $arreglo_equipo["high_auto_bot"] = 0;
        $arreglo_equipo["high_tele_up"] = 0;
        $arreglo_equipo["high_tele_bot"] = 0;
        foreach ($respuestas as $form) {
          if ($form["numero_equipo"] === $arreglo_equipo["numero"]) {
            $arreglo_equipo["avg_auto_up"] += $form["auto_upper"];
            $arreglo_equipo["avg_auto_bot"] += $form["auto_bottom"];
            $arreglo_equipo["avg_tele_up"] += $form["teleop_upper"];
            $arreglo_equipo["avg_tele_bot"] += $form["teleop_bottom"];
            if ($form["auto_upper"] > $arreglo_equipo["high_auto_up"]) {
              $arreglo_equipo["high_auto_up"] = $form["auto_upper"];
            }
            if ($form["auto_bottom"] > $arreglo_equipo["high_auto_bot"]) {
              $arreglo_equipo["high_auto_bot"] = $form["auto_bottom"];
            }
            if ($form["teleop_upper"] > $arreglo_equipo["high_tele_up"]) {
              $arreglo_equipo["high_tele_up"] = $form["teleop_upper"];
            }
            if ($form["teleop_bottom"] > $arreglo_equipo["high_tele_bot"]) {
              $arreglo_equipo["high_tele_bot"] = $form["teleop_bottom"];
            }
            $contador++;
          }
        }
        $arreglo_equipo["avg_auto_up"] /= $contador;
        $arreglo_equipo["avg_auto_bot"] /= $contador;
        $arreglo_equipo["avg_tele_up"] /= $contador;
        $arreglo_equipo["avg_tele_bot"] /= $contador;
        if ($form["endgame"] === "Colgarse") {
          $arreglo_equipo["endgame"] = "Colgarse";
        } else if($form["endgame"] === "Estacionarse"){
          $arreglo_equipo["endgame"] = "Estacionarse";
        } else if($form["endgame"] === "Score Power Cells"){
          $arreglo_equipo["endgame"] = "Score Power Cells";
        } else {
          $arreglo_equipo["endgame"] = "N/A";
        }
        $resumen_equipos[$arreglo_equipo["numero"]] = $arreglo_equipo;

        foreach ($resumen_equipos as $key => $value) {
          echo "
          <div class='team centre'>
            <h2>${value['nombre']} ${value['numero']}</h2>
            <h4>Power Cells</h4>
            <h5>Promedio</h5>
            <table class='estadisticas'>
              <th colspan='2'>Autonomous</th>
              <th colspan='2'>Teleop</th>
              <tr>
                <th>Outer Port</th>
                <th>Bottom Port</th>
                <th>Outer Port</th>
                <th>Bottom Port</th>
              </tr>
              <tr>
                <td>${value['avg_auto_up']}</td>
                <td>${value['avg_auto_bot']}</td>
                <td>${value['avg_tele_up']}</td>
                <td>${value['avg_tele_bot']}</td>
              </tr>
            </table>
            <h5>Highest score</h5>
            <table class='estadisticas'>
              <th colspan='2'>Autonomous</th>
              <th colspan='2'>Teleop</th>
              <tr>
                <th>Outer Port</th>
                <th>Bottom Port</th>
                <th>Outer Port</th>
                <th>Bottom Port</th>
              </tr>
              <tr>
                <td>${value['high_auto_up']}</td>
                <td>${value['high_auto_bot']}</td>
                <td>${value['high_tele_up']}</td>
                <td>${value['high_tele_bot']}</td>
              </tr>
            </table>
            <p>Carga max: ${value['carga']}</p>
            <p>Cadencia: ${value['cadencia']} seg</p>

            <div class='mitad'>
              <h4>Switch generator</h4>
              <img src='${value['switch']}' alt='Se cuelga' class='imagencita'>
              <p>Peso: ${value['peso']} lbs</p>
              <h4>Endgame: ${value['endgame']}</h4>
            </div>
            <div class='mitad'>
              <h4>Robot</h4>";
              echo ($value["buscar"] ? "<img src='${value['chasis']}' class='imagencita'>": "<p>${value['chasis']}</p>");
              echo "
              <p>Altura: ${value['altura']} in</p>
            </div>
          </div>
          ";
        }
      }

      ?>
    </div>

    <footer>
      <div class="centre firma flexbox">
        <a href="https://github.com/DiegoMont" class="firma">&lt;/&gt; with <span id="corazon" class="firma">&#9829;</span> by <span class="firma">DiegoMont</span></a>
      </div>
    </footer>
  </body>
</html>
