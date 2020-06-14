function llenarEspacioNav() {
  const divEspacioNav = document.getElementById("espacio-nav");
  let alturaNav = document.querySelector("nav").offsetHeight;
  console.log(alturaNav);
  divEspacioNav.style.height = alturaNav + "px";
}

window.onload = llenarEspacioNav;
window.addEventListener('resize', llenarEspacioNav);
