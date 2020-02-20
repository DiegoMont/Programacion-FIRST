<html lang=es dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultados Scouting</title>
    <link rel="stylesheet" type="text/css" href="general.css">
    <link rel="stylesheet" type="text/css" href="estiloResultados.css">
  </head>
  <body>
    <div class="centre">
      <h1>Resultados</h1>
      <div class="grid">
        <h2>Equipo</h2>
        <h2># Match</h2>
        <h2>Habitat inicial</h2>
        <h2>Sandstorm</h2>
        <h2>Cargos</h2>
        <h2>Hatch Panels</h2>
        <h2>End Game Habitat</h2>
        <!--<h2>Score</h2>-->
        <?php
        $conn = mysqli_connect("localhost", "root", "", "bd_scouting");
        $SQLquery = "SELECT * FROM datos";
        $result = mysqli_query($conn, $SQLquery);
        while($row=mysqli_fetch_row($result)){
          echo "<h3>".$row[1]."</h3>";
          echo "<h3>".$row[2]."</h3>";
          echo "<h3 class='hab-inicial'>".$row[3]."</h3>";
          echo "<h3 class='sandstorm'>".$row[4]."</h3>";
          echo "<h3 class='cargos'>".$row[5]."</h3>";
          echo "<h3 class='hatch-panels'>".$row[6]."</h3>";
          echo "<h3 class='hab-inicial'>".$row[7]."</h3>";
          //echo "<h3 class='score'></h3>";
        }
        mysqli_close($conn);
        ?>
      </div>
      <a href="encuesta.html">Hacer Scouting</a>
    </div>
  </body>
</html>
