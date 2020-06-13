function verificar(){
  const inputPassword = document.getElementById("pass");
  if (inputPassword.value === "") {
    const pError = document.getElementById("error-pass");
    pError.innerHTML = "La contrase&ntilde;a est&aacute; vac&iacute;a";
    pError.classList.remove("ocultar");
    return false;
  } else
    return true;
}