<?php
function checarNumero(&$valor_post, $minimo, $maximo){
  if (isset($valor_post)) {
    $temp = htmlspecialchars($valor_post);
    $temp = filter_var(filter_var($temp, FILTER_SANITIZE_NUMBER_INT), FILTER_VALIDATE_INT, ["options" => ["min_range" => $minimo, "max_range" => $maximo]]);
    return $temp;
  }
}

function validarDouble(&$valor_post, $minimo, $maximo){
  if (isset($valor_post)) {
    $temp = htmlspecialchars($valor_post);
    $temp = filter_var($temp, FILTER_VALIDATE_FLOAT);
    if(!$temp)
      return 0;
    else if($temp >= $minimo && $temp <= $maximo)
      return $temp;
  }
}

function validarTexto(&$valor_post){
  if(isset($valor_post)) {
    $temp = trim(htmlspecialchars($valor_post));
    return substr($temp, 0, 255);
  }
}
?>
