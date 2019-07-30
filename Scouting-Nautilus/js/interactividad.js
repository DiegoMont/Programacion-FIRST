//Variables
let valoresContadores  = document.getElementsByClassName("cifra-contador");
let labelRadioImg = document.getElementsByClassName("img-opc");
let divsSeleccionHabitats = document.getElementsByClassName("habitats");

//Funciones
function agregarOpacar(arregloElementos) {
  for (let i = 0; i < arregloElementos.length; i++) {
    arregloElementos[i].classList.add("opacar");
  }
}

function destacarLabelSiChecked(arregloLabels) {
  agregarOpacar(arregloLabels);
  for (let i = 0; i < arregloLabels.length; i++) {
    let inputLigado = arregloLabels[i].querySelector("input");
    if (inputLigado.checked) {
      arregloLabels[i].classList.remove("opacar");
    }
  }
}

//Funcionamiento de contadores numericos
if (valoresContadores.length > 0) {
  let botonesAumentar = document.getElementsByClassName("aumentar");
  let botonesRestar = document.getElementsByClassName("restar");
  for (let i = 0; valoresContadores.length > i; i++) {
    //Botones aumentar
    botonesAumentar[i].addEventListener("click", function() {
      let valorNumerico = valoresContadores[i].value;
      valorNumerico++;
      valoresContadores[i].value = valorNumerico;
    });

    //Botones disminuir
    botonesRestar[i].addEventListener("click", function() {
      let valorNumerico = valoresContadores[i].value;
      valorNumerico--;
      valoresContadores[i].value = valorNumerico;
    });
  }
}

//Funcionamiento para preguntas de botones radio con imagenes
if (labelRadioImg.length > 0) {
  destacarLabelSiChecked(labelRadioImg);
  for (let i = 0; i < labelRadioImg.length; i++) {
    labelRadioImg[i].addEventListener("click", function() {
      destacarLabelSiChecked(labelRadioImg);
    });
  }
}

//Funcionamiento para selector de nivel de habitat en Deep Space
if (divsSeleccionHabitats.length > 0) {
  for (let i = 0; i < divsSeleccionHabitats.length; i++) {
    let habitatLevels = divsSeleccionHabitats[i].getElementsByTagName("label");
    agregarOpacar(habitatLevels);
    for (let j = 2; j < habitatLevels.length; j++) {
      habitatLevels[j].addEventListener("click", function() {
        agregarOpacar(habitatLevels);
        habitatLevels[j].classList.remove("opacar");
      });
    }
    for (let j = 0; j < 2; j++) {
      habitatLevels[j].addEventListener("click", function() {
        agregarOpacar(habitatLevels);
        for (let k = 0; k < 2; k++) {
          habitatLevels[k].classList.remove("opacar");
        }
      });
    }
  }
}
