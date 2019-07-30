function verificar() {
  let comprobados = [false, false, false, false, false]
  let errores = Array.from(document.getElementsByClassName("error"));
  errores.forEach(function(elemento){
    elemento.classList.add("ocultar");
  });

  let numTeam = document.getElementById("Team-Number");
  if (Number(numTeam.value) > 0) {
    comprobados[0] = true;
  } else {
    errores[0].classList.remove("ocultar");
  }

  let numMatch = document.getElementById("Match-Number");
  if (Number(numMatch.value) > 0) {
    comprobados[1] = true;
  } else {
    errores[1].classList.remove("ocultar");
  }

  if (!(Array.from(document.getElementsByName("hab-start")).some(function(elemento){
    return elemento.checked;
  }))) {
    errores[2].classList.remove("ocultar");
  } else {
    comprobados[2] = true;
  }

  if (!(Array.from(document.getElementsByName("sandstorm")).some(function(elemento){
    return elemento.checked;
  }))) {
    errores[3].classList.remove("ocultar");
  } else {
    comprobados[3] = true;
  }

  if (!(Array.from(document.getElementsByName("hab-final")).some(function(elemento){
    return elemento.checked;
  }))) {
    errores[4].classList.remove("ocultar");
  } else {
    comprobados[4] = true;
  }

  document.getElementById("Cargo-Number").value = Number(document.getElementById("num-cargos").innerHTML);
  console.log(document.getElementById("Cargo-Number"));

  document.getElementById("Hatches-Number").value = Number(document.getElementById("num-hatch").innerHTML);
  console.log(document.getElementById("Hatches-Number"));

  return comprobados.every(function(test){
    return test;
  });
}
