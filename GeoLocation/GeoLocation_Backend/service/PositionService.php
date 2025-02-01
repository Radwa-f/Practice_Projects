<?php
include_once 'dao/IDao.php'; 
include_once 'classe/Position.php'; 
include_once 'connexion/Connexion.php';

class PositionService implements IDao {
    private $connexion;

    public function __construct() { 
        $this->connexion = new Connexion();
    }

    public function create($position) {
        $query = "INSERT INTO position (latitude, longitude, date, imei, ip) VALUES (:latitude, :longitude, :date, :imei, :ip)";
        $req = $this->connexion->getConnextion()->prepare($query);

        $req->bindParam(':latitude', $position->getLatitude());
        $req->bindParam(':longitude', $position->getLongitude());
        $req->bindParam(':date', $position->getDate());
        $req->bindParam(':imei', $position->getImei());
        $req->bindParam(':ip', $position->getIp());  

        try {
            $req->execute();
        } catch (PDOException $e) {
            error_log("Database error: " . $e->getMessage());

        }
    }

    public function delete($obj) { 

    }
    
    public function getAll() {
        $query = "select * from position";
        $req = $this->connexion->getConnextion()->prepare($query); $req->execute();
        return $req->fetchAll(PDO::FETCH_ASSOC);
        }
        
    
    public function getById($obj) { 

    }
    
    public function update($obj) { 

    }

    public function findAll(): void {

    }
    
    public function findById($id): void {

    }
}
