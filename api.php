<?php
include('conectDB.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    if (isset($_POST['id']) && isset($_POST['nombre'])) {

        $id = $_POST['id'];
        $nombre = $_POST['nombre']; 

        
        obtenerResultado("INSERT INTO usuarios (id, nombre) VALUES ('$id', '$nombre')");

      
        echo json_encode(array("message" => "Usuario insertado correctamente"));
    } else {
        
        http_response_code(400); // Bad Request
        echo json_encode(array("message" => "Error en la solicitud: faltan datos 'id' o 'nombre'"));
    }
} else {
        http_response_code(405); 
    echo json_encode(array("message" => "Método no permitido"));
}
?>
