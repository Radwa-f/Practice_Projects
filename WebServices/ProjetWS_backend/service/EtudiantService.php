<?php
namespace service;

use classes\Etudiant;
use connexion\Connexion;
use IDao;
use PDO;

include_once RACINE . '/classes/Etudiant.php';
include_once RACINE . '/connexion/Connexion.php';
include_once RACINE . '/dao/IDao.php';

class EtudiantService implements IDao {

    private $connexion;

    function __construct() {
        $this->connexion = new Connexion();
    }

    public function create($o) {
        $query = "INSERT INTO Etudiant (`nom`, `prenom`, `ville`, `sexe`, `photo`) "
            . "VALUES (:nom, :prenom, :ville, :sexe, :photo);";
    
        $req = $this->connexion->getConnexion()->prepare($query);
    
        // Bind parameters
        $req->bindParam(':nom', $o->getNom());
        $req->bindParam(':prenom', $o->getPrenom());
        $req->bindParam(':ville', $o->getVille());
        $req->bindParam(':sexe', $o->getSexe());
        $req->bindParam(':photo', $o->getPhoto(), PDO::PARAM_LOB);  // Bind the photo as BLOB
    
        if ($req->execute()) {
            return true;
        } else {
            die('Erreur SQL: ' . implode(", ", $req->errorInfo()));
        }
    }
    

    public function delete($o) {
        $query = "delete from Etudiant where id = " . $o->getId();
        $req = $this->connexion->getConnexion()->prepare($query);
        $req->execute() or die('Erreur SQL');
    }

    public function findAll() {
        $etds = array();
        $query = "select * from Etudiant";
        $req = $this->connexion->getConnexion()->prepare($query);
        $req->execute();
        while ($e = $req->fetch(PDO::FETCH_OBJ)) {
            // Encode the photo data back to Base64 before sending it to the frontend
            $photo = base64_encode($e->photo);
            $etds[] = new Etudiant($e->id, $e->nom, $e->prenom, $e->ville, $e->sexe, $photo);
        }
        return $etds;
    }
    

    public function findById($id) {
        $query = "select * from Etudiant where id = " . $id;
        $req = $this->connexion->getConnexion()->prepare($query);
        $req->execute();
        if ($e = $req->fetch(PDO::FETCH_OBJ)) {
            $etd = new Etudiant($e->id, $e->nom, $e->prenom, $e->ville, $e->sexe, $e->photo);
        }
        return $etd;
    }

    public function update($o) {
        $query = "UPDATE `etudiant` SET `nom` = :nom, `prenom` = :prenom, `ville` = :ville, `sexe` = :sexe WHERE `id` = :id";
        $req = $this->connexion->getConnexion()->prepare($query);
        
        // Bind parameters
        $req->bindParam(':id', $o->getId(), PDO::PARAM_INT);
        $req->bindParam(':nom', $o->getNom());
        $req->bindParam(':prenom', $o->getPrenom());
        $req->bindParam(':ville', $o->getVille());
        $req->bindParam(':sexe', $o->getSexe());
    
        return $req->execute(); // Return true if update succeeded, false otherwise
    }
    

    public function findAllApi() {
        $query = "select * from Etudiant";
        $req = $this->connexion->getConnexion()->prepare($query);
        $req->execute();
        return $req->fetchAll(PDO::FETCH_ASSOC);
    }
}