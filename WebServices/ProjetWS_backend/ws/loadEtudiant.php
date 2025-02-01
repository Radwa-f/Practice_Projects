<?php

use service\EtudiantService;

if ($_SERVER["REQUEST_METHOD"] == "GET") {  // Using GET for fetching data
    include_once '../racine.php';
    include_once RACINE . '/service/EtudiantService.php';
    loadAll();
}

function loadAll() {
    $es = new EtudiantService();

    header('Content-type: application/json');
    // Fetch all students directly from the service
    $students = $es->findAll(); // This method fetches Etudiant objects directly

    // Convert the Etudiant objects to an associative array to return as JSON
    $etudiantsArray = array_map(function($etudiant) {
        return [
            'id' => $etudiant->getId(),
            'nom' => $etudiant->getNom(),
            'prenom' => $etudiant->getPrenom(),
            'ville' => $etudiant->getVille(),
            'sexe' => $etudiant->getSexe(),
            'photo' => $etudiant->getPhoto(), // Sending the BLOB directly
        ];
    }, $students);

    echo json_encode($etudiantsArray);
}
