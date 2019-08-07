//Variables
let valoresContadores  = document.getElementsByClassName("cifra-contador");
let labelRadioImg = document.getElementsByClassName("img-opc");
let divsSeleccionHabitats = document.getElementsByClassName("habitats");
let labelImgCheckbox = document.getElementsByClassName("img-check");
let divBtnBool = document.getElementsByClassName("btn-bool");
let contadoresColumna = document.getElementsByClassName("cifra-col-ctr");

//Funciones
function agregarOpacar(arregloElementos) {
  for (let i = 0; i < arregloElementos.length; i++) {
    arregloElementos[i].classList.add("opacar");
  }
}

function destacarLabelsSiChecked(arregloLabels) {
  agregarOpacar(arregloLabels);
  for (let i = 0; i < arregloLabels.length; i++) {
    destacarLabelSiChecked(arregloLabels[i]);
  }
}

function destacarLabelSiChecked(labelElement) {
  let inputLigado = labelElement.querySelector("input");
  if (inputLigado.checked) {
    labelElement.classList.remove("opacar");
  } else {
    labelElement.classList.add("opacar");
  }
}

function modificarValueInput(elemento, operacion = 1) {
  let valorNumerico = Number(elemento.value)
  valorNumerico += operacion;
  elemento.value = valorNumerico;
}

//Funcionamiento de contadores numericos
if (valoresContadores.length > 0) {
  let botonesAumentar = document.getElementsByClassName("aumentar");
  let botonesRestar = document.getElementsByClassName("restar");
  for (let i = 0; valoresContadores.length > i; i++) {
    //Botones aumentar
    botonesAumentar[i].addEventListener("click", function() {
      modificarValueInput(valoresContadores[i]);
    });

    //Botones disminuir
    botonesRestar[i].addEventListener("click", function() {
      modificarValueInput(valoresContadores[i], -1);
    });
  }
}

//Funcionamiento para preguntas de botones radio con imagenes
if (labelRadioImg.length > 0) {
  destacarLabelsSiChecked(labelRadioImg);
  for (let i = 0; i < labelRadioImg.length; i++) {
    labelRadioImg[i].addEventListener("click", function() {
      destacarLabelsSiChecked(labelRadioImg);
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

//Funcionamiento para preguntas de checkbox con imagenes
if (labelImgCheckbox.length > 0) {
  agregarOpacar(labelImgCheckbox);
  for (let i = 0; i < labelImgCheckbox.length; i++) {
    labelImgCheckbox[i].addEventListener("click", function() {
      destacarLabelSiChecked(labelImgCheckbox[i]);
    });
  }
}

//Funcionamiento para botones booleanos
if (divBtnBool.length > 0) {
  for (let i = 0; i < divBtnBool.length; i++) {
    let labelsBotones = divBtnBool[i].getElementsByTagName("label");
    destacarLabelsSiChecked(labelsBotones);
    for (let j = 0; j < labelsBotones.length; j++) {
      labelsBotones[j].addEventListener("click", function() {
        destacarLabelsSiChecked(labelsBotones);
      });
    }
  }
}

//Funcionamiento para contadores en columna
if (contadoresColumna.length > 0) {
  for (let i = 0; i < contadoresColumna.length; i++) {
    contadoresColumna[i].addEventListener("click", function() {
      modificarValueInput(contadoresColumna[i]);
    });
    contadoresColumna[i].addEventListener("dblclick", function() {
      modificarValueInput(contadoresColumna[i], -3);
    });
  }
}
