<?php
session_start(['cookie_lifetime' => 172800]);
if (isset($_SESSION["sesionIniciada"])) {
  if ($_SESSION["sesionIniciada"] == 125) {
    header("Location: resultados.php");
    exit;
  }
}
?>

<!DOCTYPE html>
<html lang="es" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesion</title>
    <link rel="stylesheet" type="text/css" href="../css/Reset.css">
    <link rel="stylesheet" type="text/css" href="../css/general.css">
    <style media="screen">
      .flexbox {
        width: 100vw;
        height: 100vh;
      }

      .login {
        background-color: #fbfaf9;
        padding: 2rem;
        border-radius: 2rem;
      }

      input[type=password] {
        background-color: #090909;
        margin: 0.5rem;
      }

      input[type=submit] {
        color: #f3e216;
        background-color: #090909;
        display: block;
        padding: 0.5rem 1rem;
        border: 5px solid #f3e216;
        margin: 0 auto;
      }

      input[type=submit]:hover {
        background-color: #f3e216;
        color: #090909;
      }
    </style>
  </head>
  <body>
    <div class="flexbox">
      <div class="login">
        <h1 class="dark-font">Scouting</h1>
        <form action="index.php" method="POST">
          <label class="dark-font" for="pass">Contrase&ntilde;a:</label><br>
          <input type="password" name="contrasenia" placeholder="contraseña" id="pass"><br>
          <?php
          if (isset($_POST["contrasenia"])) {
            $contrasenia = htmlspecialchars($_POST["contrasenia"]);
            if($contrasenia === "dieguapo") {
              $_SESSION["sesionIniciada"] = 125;
              header("Location: resultados.php");
              exit;
            } else {
              echo "<p class='error'>Contrase&ntildea incorrecta</p>";
            }
          }
          ?>
          <input class="btn" type="submit" name="submit" value="Log in">
        </form>
      </div>
    </div>
  </body>
</html>
