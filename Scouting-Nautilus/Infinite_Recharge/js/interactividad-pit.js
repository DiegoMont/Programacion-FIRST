//Pregunta adicional drivetrain
const divDrivetrainAdicional = document.getElementById("drivetrain-adicional");
const respuestasTipoDrivetrain = Array.from(document.getElementsByName("drivetrain"));
function togglePregunta() {
  if (respuestasTipoDrivetrain[4].checked)
    divDrivetrainAdicional.classList.remove("ocultar");
  else
    divDrivetrainAdicional.classList.add("ocultar");
}
respuestasTipoDrivetrain.forEach(item => {
  item.addEventListener('click', togglePregunta);
});
togglePregunta();


//Preguntas adicional Shield Generator
const divSwitchOpcionales = document.getElementById("switch-adicional");
const respuestasSwitch = document.getElementsByName("shield-generator");
function togglePreguntaSwitch() {
  if (respuestasSwitch[0].checked)
    divSwitchOpcionales.classList.remove("ocultar");
  else
    divSwitchOpcionales.classList.add("ocultar");
}
respuestasSwitch.forEach(item => {
  item.addEventListener('click', togglePreguntaSwitch);
});
togglePreguntaSwitch();

//Pregunta control panel
const divRotation = document.getElementById("pregunta-rotation");
const respuestasControl = document.getElementsByName("control-panel");
function togglePreguntaPanel() {
  if (respuestasControl[0].checked)
    divRotation.classList.remove("ocultar");
  else
    divRotation.classList.add("ocultar");
}
respuestasControl.forEach(item => {
  item.addEventListener('click', togglePreguntaPanel);
});
togglePreguntaPanel();
