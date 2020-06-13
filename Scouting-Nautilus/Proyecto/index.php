<?php
//Constante para contraseña
define('PASSWORD', "futuroswinners");
define('KEY_SESION', "inicializada");

session_start(['cookie_lifetime' => 86400]);

//Mensaje de error
$mensaje = "";

//Si ya hay una sesion iniciada, redirigir a menu
if (isset($_SESSION["sesion"])) {
  if ($_SESSION["sesion"] === KEY_SESION) {
    header("Location:menu.php");
    exit;
  }
}

//Si ya dieron click en el boton de submit
if (isset($_POST["btn-submit"])) {

  //Checar si enviaron la contraseña
  if (isset($_POST["contrasena"])) {
    if ($_POST["contrasena"] === PASSWORD) {
      //Si la contraseña es correcta iniciar sesion y redirigir|
      $_SESSION["sesion"] = KEY_SESION;
      header("Location:menu.php");
      exit;
    } else {
      //Si la contraseña es incorrecta mandar error
      $mensaje = "<p class='error'>La contrase&ntilde;a es incorrecta</p>";
    }
  } else {
    //Si el input esta vacio mandar error
    $mensaje = "<p class='error'>Ingresa una contrase&ntilde;a</p>";
  }
}
?>
<!DOCTYPE html>
<html lang="es" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Scouting</title>
    <link rel="stylesheet" type="text/css" href="css/Reset.css">
    <link rel="stylesheet" type="text/css" href="css/general.css">
    <link rel="stylesheet" type="text/css" href="css/estilo-index.css">
    <link rel="icon" type="image/png" href="img/nautilus.png">
    <script src="js/seguridad-index.js" charset="utf-8" defer></script>
  </head>
  <body>
    <header>
      <div id="logo-header">
        <h2>Scouting</h2>
        <img src="img/LogoHeader.png" alt="Nautilus 40 10">
      </div>
    </header>
    <section>
      <div class="login">
        <h1 class="login">SIGN IN</h1>
        <form action="index.php" method="POST" onsubmit="return verificar()">
          <label for="pass" class="login">Contrase&ntilde;a:</label>
          <input type="password" name="contrasena" id="pass" placeholder="Contrase&ntilde;a" class="login">
          <p class="error ocultar" id="error-pass"></p>
          <?php echo $mensaje; ?>
          <button type="submit" name="btn-submit" id="btn-submit" value="Log in">Log in</button>
        </form>
      </div>
    </section>
    
    <footer>
      <div class="centre firma flexbox">
        <a href="https://github.com/DiegoMont" class="firma">&lt;/&gt; with <span id="corazon" class="firma">&#9829;</span> by <span class="firma">DiegoMont</span></a>
      </div>
    </footer>
  </body>
</html>
