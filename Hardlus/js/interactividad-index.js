const btnEspaniol = document.getElementById("espanol");
const btnIngles = document.getElementById("ingles");

function manualCubrebocas() {
  const manualEspanol = document.getElementById("manual-cubrebocas-spanish");
  const manualIngles = document.getElementById("manual-cubrebocas-english");
  manualEspanol.classList.add("ocultar");
  manualIngles.classList.add("ocultar");
  if (btnIngles.classList.contains("idioma-activo")) {
    manualIngles.classList.remove("ocultar");
  } else {
    manualEspanol.classList.remove("ocultar");
  }
}

btnEspaniol.addEventListener("click", function () {
  btnIngles.classList.remove("idioma-activo");
  btnEspaniol.classList.add("idioma-activo");
  manualCubrebocas();
});

btnIngles.addEventListener("click", function () {
  btnIngles.classList.add("idioma-activo");
  btnEspaniol.classList.remove("idioma-activo");
  manualCubrebocas();
});

manualCubrebocas();

// Foto frase motivadora

function alturaImagen() {
  const alturaGrid = document.getElementById("frase").getElementsByClassName("frase-izq")[0].offsetHeight;
  //console.log(alturaGrid);
  const elementoImg = document.getElementById("foto-ingenieria");
  if (document.getElementById("frase").offsetWidth > 700) {
    elementoImg.style.height = alturaGrid+"px";
  } else {
    console.log("altura auto");
    elementoImg.style.height = "auto";
  }
}

alturaImagen();
window.addEventListener("resize", alturaImagen);
