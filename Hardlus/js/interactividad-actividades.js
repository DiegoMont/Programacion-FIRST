function contarActividades() {
  const arregloActividades = document.getElementsByClassName("actividad");
  const parrafoCuenta = document.getElementById("cuenta-act");
  let n = arregloActividades.length;
  parrafoCuenta.innerText = (n == 1 ? "1 actividad": n + " actividades");
}

contarActividades();
