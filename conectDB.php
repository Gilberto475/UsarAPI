<?php
header('Content-type: text/html;charset=uft-8');
function obtenerResultado($query){
    $mysqli =new mysqli ("localhost","root","","claseandroid");
    if ($mysqli->multi_query($query)){
        return $mysqli->store_result();
    }
    $mysqli->close ();
}
?>
