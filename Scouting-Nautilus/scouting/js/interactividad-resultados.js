const btnTabla = document.getElementById("boton-tablas");
const btnEquipos = document.getElementById("boton-teams");

function botones() {
  Array.from(document.getElementsByClassName("btn-display")).forEach(item => {
    item.classList.remove("active");
  });
}

function ocultarPantallas(claseSecciones) {
  Array.from(document.getElementsByClassName('seccion')).forEach(i => {
    i.classList.add('ocultar');
  });
  Array.from(document.getElementsByClassName(claseSecciones)).forEach(item => {
    item.classList.remove("ocultar");
  });
}

ocultarPantallas("tablitas");

btnTabla.addEventListener('click', function(){
  botones();
  ocultarPantallas("tablitas");
  btnTabla.classList.add("active");
});

btnEquipos.addEventListener('click', function(){
  botones();
  btnEquipos.classList.add("active");
  ocultarPantallas("teams");
});
