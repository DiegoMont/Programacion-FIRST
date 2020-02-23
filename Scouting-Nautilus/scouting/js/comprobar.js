function verificar() {
  let errorFree = true;
  const errores = Array.from(document.getElementsByClassName("error"));
  errores.forEach(elemento => {
    elemento.classList.add("ocultar");
  });


  //Errores en el numero de equipo
  const equipo = document.getElementById("Team-Number");
  if(equipo.value < 1000){
    errorFree = false;
    errores[0].classList.remove("ocultar");
  }

  //Errores en el numero de match
  const match = document.getElementById("Match-Number");
  if (match.value < 1 || match.value > 200) {
    errorFree = false;
    errores[1].classList.remove("ocultar");
  }

  //Contadores power port
  if(document.getElementById("auto-outer").value < 0 || document.getElementById("auto-abajo").value < 0 || document.getElementById("teleop-target").value < 0 || document.getElementById("teleop-abajo").value < 0) {
    errorFree = false;
    errores[2].classList.remove("ocultar");
  }

  //Contador numero Fouls
  const faltas = document.getElementById("faltas").value;
  console.log(faltas);
  if (faltas < 0 || faltas > 50) {
    errorFree = false;
    errores[6].classList.remove("ocultar");
  }

  //Pregunta Rotation control
  const respuestas = Array.from(document.getElementsByName("rotation-control"));
  if(!respuestas.some(radio => {
    return radio.checked;
  })) {
    errorFree = false;
    errores[3].classList.remove("ocultar");
  }

  //Pregunta Position control
  const respuestas2 = Array.from(document.getElementsByName("position-control"));
  if(!respuestas2.some(radio => {
    return radio.checked;
  })) {
    errorFree = false;
    errores[5].classList.remove("ocultar");
  }

  //Pregunta Generator Switch
  const respuestas1 = Array.from(document.getElementsByName("shield-generator"));
  if(!respuestas1.some(radio => {
    return radio.checked;
  })) {
    errorFree = false;
    errores[4].classList.remove("ocultar");
  }

  return errorFree;
}
