<?php

use classes\Etudiant;
use service\EtudiantService;

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once '../racine.php';
    include_once RACINE . '/service/EtudiantService.php';
    update();
}

function update() {
    // Check if required parameters are set
    if (!isset($_POST['id'], $_POST['nom'], $_POST['prenom'], $_POST['ville'], $_POST['sexe'])) {
        header('Content-type: application/json');
        echo json_encode([
            "message" => "Missing required parameters.",
            "status" => "error"
        ]);
        return;
    }

    // Extract data from the POST request
    $id = $_POST['id'];
    $nom = $_POST['nom'];
    $prenom = $_POST['prenom'];
    $ville = $_POST['ville'];
    $sexe = $_POST['sexe'];

    $es = new EtudiantService();

    // Create a new Etudiant instance with the provided data
    $etudiantToUpdate = new Etudiant($id, $nom, $prenom, $ville, $sexe, null); // Pass null for photo since it won't be updated

    // Call the update method in EtudiantService
    $updateSuccess = $es->update($etudiantToUpdate);

    if ($updateSuccess) {
        // Return a confirmation response
        header('Content-type: application/json');
        echo json_encode([
            "message" => "Student with ID $id has been updated.",
            "status" => "success"
        ]);
    } else {
        // Handle the error case if the update failed
        header('Content-type: application/json');
        echo json_encode([
            "message" => "Failed to update student with ID $id.",
            "status" => "error"
        ]);
    }
}
