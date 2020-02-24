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

  //Errores en recolectores
  const recolectores = Array.from(document.getElementsByName("collectors"));
  if(!recolectores.some(radio => {
    return radio.checked;
  })) {
    errorFree = false;
    errores[1].classList.remove("ocultar");
  }

  //Errores en almacenamiento de Power Cells
  const storageCells = document.getElementById("almacenamiento-power-cell");
  if (storageCells.value < 1 || storageCells.value > 5) {
    errorFree = false;
    errores[2].classList.remove("ocultar");
  }

  //Errores en eleccion de drivetrain
  const drivetrainType = Array.from(document.getElementsByName("drivetrain"));
  if(!drivetrainType.some(radio => {
    return radio.checked;
  })) {
    errorFree = false;
    errores[3].classList.remove("ocultar");
  }

  const drivetrainAlterno = document.getElementById("otro-drivetrain");
  if(drivetrainType[4].checked && drivetrainAlterno.value === "") {
    errorFree = false;
    errores[4].classList.remove("ocultar");
  }

  //Pregunta Generator Switch
  const respuestas1 = Array.from(document.getElementsByName("shield-generator"));
  if(!respuestas1.some(radio => {
    return radio.checked;
  })) {
    errorFree = false;
    errores[5].classList.remove("ocultar");
  }

  const posicionesSwitch = Array.from(document.getElementsByName("position-switch"));
  if(respuestas1[0].checked && !posicionesSwitch.some(
    radio => {
      return radio.checked;
    })) {
      errorFree = false;
      errores[6].classList.remove("ocultar");
    }

    const cargarRobot = Array.from(document.getElementsByName("carry"));
    if(respuestas1[0].checked && !cargarRobot.some(radio => {
      return radio.checked;
    })) {
      errorFree = false;
      errores[7].classList.remove("ocultar");
    }

    //Pregunta especialidad
    const especialidad = document.getElementById("especialidad");
    if(especialidad.value === "") {
      errorFree = false;
      errores[8].classList.remove("ocultar");
    } else if(especialidad.value.length < 6) {
      errorFree = false;
      errores[9].classList.remove("ocultar");
    }

    //Pregunta necesidades de alianza
    const necesidades = document.getElementById("necesidad-alianza").value;
    if(necesidades === "" || necesidades.length < 6) {
      errorFree = false;
      errores[10].classList.remove("ocultar");
    }

    //Seleccion de Ports
    const ports = Array.from(document.getElementsByName("ports"));
    if(!ports.some(radio => {
      return radio.checked;
    })) {
      errorFree = false;
      errores[11].classList.remove("ocultar");
    }

    //Pregunta Sistema de vision
    const vision = Array.from(document.getElementsByName("vision-enabled"));
    if(!vision.some(radio => {
      return radio.checked;
    })) {
      errorFree = false;
      errores[12].classList.remove("ocultar");
    }

    //Preguntas del Control Panel
    const controlPanel = Array.from(document.getElementsByName("control-panel"));
    if(!controlPanel.some(radio => {
      return radio.checked;
    })) {
      errorFree = false;
      errores[13].classList.remove("ocultar");
    }

    const positionControl = Array.from(document.getElementsByName("position-control"));
    if(controlPanel[0].checked && !positionControl.some(radio => {
      return radio.checked;
    })) {
      errorFree = false;
      errores[14].classList.remove("ocultar");
    }

    //Pregunta cadencia
    const cadencia = document.getElementById("cadencia").value;
    if(cadencia === "" || cadencia < 0.01 || cadencia > 300) {
      errorFree = false;
      errores[15].classList.remove("ocultar");
    }

    //Pregunta peso
    const peso = document.getElementById("peso").value;
    if(peso === "" || peso < 5 || peso > 125) {
      errorFree = false;
      errores[16].classList.remove("ocultar");
    }

    //Pregunta altura
    const altura = document.getElementById("altura").value;
    if(altura === "" || altura < 1 || altura > 45) {
      errorFree = false;
      errores[17].classList.remove("ocultar");
    }

  return errorFree;
}
