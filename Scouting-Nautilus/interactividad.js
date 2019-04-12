/*window.onscroll = function(){
  if (document.body.scrollTop > 55 || document.documentElement.scrollTop > 55) {
    document.getElementById("fix-info").style.position = "fixed";
    document.querySelector("form").style.position="relative";
    document.querySelector("form").style.top="47px";
  } else {
    document.getElementById("fix-info").style.position = "static";
    document.querySelector("form").style.position = "static";
  }
}
*/

let opcHabInl = document.getElementsByClassName("habs-inl");
let opcHabFnl = document.getElementsByClassName("habs-fnl");
let opcSand = document.getElementsByClassName("opc-sand");
let numCargos = document.getElementById("num-cargos");
let numHatches = document.getElementById("num-hatch");

function agregarOpacar(arregloElementos) {
  for (let i = 0; i < arregloElementos.length; i++) {
    arregloElementos[i].classList.add("opacar");
  }
}

for (let i = 0; i < opcHabInl.length; i++) {
  opcHabInl[i].onclick = function (){
    agregarOpacar(opcHabInl);
    opcHabInl[i].classList.remove("opacar");
  };
}
agregarOpacar(opcHabInl);

for (let i = 0; i < opcHabFnl.length; i++) {
  opcHabFnl[i].onclick = function (){
    agregarOpacar(opcHabFnl);
    opcHabFnl[i].classList.remove("opacar");
  };
}
agregarOpacar(opcHabFnl);

for (let i = 0; i < opcSand.length; i++) {
  opcSand[i].onclick = function (){
    agregarOpacar(opcSand);
    opcSand[i].classList.remove("opacar");
  };
}
agregarOpacar(opcSand);

document.getElementById("sub-cargo").onclick = function(){
  let valor = Number(numCargos.innerHTML);
  valor--;
  numCargos.innerHTML = valor;
}

document.getElementById("sum-cargo").onclick = function(){
  let valor = Number(numCargos.innerHTML);
  valor++;
  numCargos.innerHTML = valor;
}

document.getElementById("sub-hatch").onclick = function(){
  let valor = Number(numHatches.innerHTML);
  valor--;
  numHatches.innerHTML = valor;
}

document.getElementById("sum-hatch").onclick = function(){
  let valor = Number(numHatches.innerHTML);
  valor++;
  numHatches.innerHTML = valor;
}
