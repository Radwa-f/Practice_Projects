<?php

use classes\Etudiant;
use service\EtudiantService;

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once '../racine.php';
    include_once RACINE . '/service/EtudiantService.php';
    delete();
}

function delete() {
    if (!isset($_POST['id'])) {
        // Handle the case where 'id' is not set
        header('Content-type: application/json');
        echo json_encode([
            "message" => "ID parameter is missing.",
            "status" => "error"
        ]);
        return;
    }

    $id = $_POST['id']; // Extract the student ID from the request
    $es = new EtudiantService();

    // Call delete method in EtudiantService, passing the student ID
    $etudiantToDelete = new Etudiant($id, '', '', '', '', ''); // Create an Etudiant instance with the ID
    $es->delete($etudiantToDelete);

    // Return a confirmation response
    header('Content-type: application/json');
    echo json_encode([
        "message" => "Student with ID $id has been deleted.",
        "status" => "success"
    ]);
}
