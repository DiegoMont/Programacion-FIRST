<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Scouting Recibido</title>
    <link rel="stylesheet" type="text/css" href="general.css">
    <style>
    h2 {
      font-size: 3rem;
      text-align: center;
      margin: 0 10px;
    }

    .centre {
      max-width: 800px;
      margin: 0 auto;
    }

    #btn-reenviar {
      text-align: center;
      font-size: 1.2rem;
      font-weight: bold;
      border: 1px solid #b4b3b3;
      padding: 1rem 2rem;
      margin: 10px auto;
      width: 6rem;
      display: block;
    }

    #btn-reenviar:hover {
      color: #000;
      background-color: #b4b3b3;
    }

    @media screen and (max-width: 800px) {
      .centre {
        padding: 0 7px;
      }
    }
    </style>
  </head>
  <body>
    <div class="centre">
      <img src="../../../../Pictures/LogoNautilus.png" alt="Nautilus 4010" style="margin-top: 0.5rem;">
      <?php
      $conn = mysqli_connect("localhost", "root", "", "bd_scouting");
      $teamNumber = $_POST["nombre-equipo"];
      $matchNumber = $_POST["numero-match"];
      $habitatStart = $_POST["hab-start"];
      $sandstorm = $_POST["sandstorm"];
      $numCargos = $_POST["cargos"];
      $numHatches = $_POST["panels"];
      $habitatEnd = $_POST["hab-final"];
      $comments = $_POST["comentarios"];
      $SQLquery = "INSERT INTO datos (equipo, numMatch, habitatI, sandstorm, cargos, hatches, habitatF, comentarios) VALUES ('$teamNumber', '$matchNumber', '$habitatStart', '$sandstorm', '$numCargos', '$numHatches', '$habitatEnd', '$comments')";
      $result = mysqli_query($conn, $SQLquery);
      if ($result) {
        echo "<h2>Se ha registrado la informaci&oacute;n del scouting exitosamente</h2>";
      } else {
        echo "<h2>¡Hubo un problema al enviar la informaci&oacute;n!</h2>";
      }
      mysqli_close($conn);
      ?>
      <a href="encuesta.html" id="btn-reenviar">Enviar otro scouting</a>
    </div>
  </body>
</html>
