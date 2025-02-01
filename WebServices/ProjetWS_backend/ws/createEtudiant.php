<?php

use classes\Etudiant;
use service\EtudiantService;

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once '../racine.php';
    include_once RACINE . '/service/EtudiantService.php';
    create();
}

function create() {
    header('Content-type: application/json'); 

    if (!isset($_POST['nom'], $_POST['prenom'], $_POST['ville'], $_POST['sexe'], $_POST['photo'])) {
        echo json_encode(["status" => "error", "message" => "Missing parameters"]);
        return;
    }
    $photo= isset($_POST['photo']) ? $_POST['photo'] : null; 

    $imageData = base64_decode($photo);
    if ($imageData === false) {
        echo json_encode(['status' => 'error', 'message' => 'Failed to decode image']);
        exit;
    }

    extract($_POST);

    $es = new EtudiantService();

    try {
        
        $etudiant = new Etudiant(null, $nom, $prenom, $ville, $sexe, $imageData);
        $es->create($etudiant);


        echo json_encode([
            "status" => "success",
            "message" => "Etudiant added successfully",
            "data" => [
                "nom" => $nom,
                "prenom" => $prenom,
                "ville" => $ville,
                "sexe" => $sexe,
                "photo" => $imageData 
            ]
        ]);
    } catch (Exception $e) {

        error_log($e->getMessage());
        echo json_encode(["status" => "error", "message" => "Failed to create student: " . $e->getMessage()]);
    }
}
