<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once 'service/PositionService.php';
    create(); 
}

function create() {

    $latitude = isset($_POST['latitude']) ? filter_var($_POST['latitude'], FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION) : null; 
    $longitude = isset($_POST['longitude']) ? filter_var($_POST['longitude'], FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION) : null; 
    $date = isset($_POST['date']) ? filter_var($_POST['date'], FILTER_SANITIZE_STRING) : null;
    $imei = isset($_POST['imei']) ? filter_var($_POST['imei'], FILTER_SANITIZE_STRING) : null;
    $ip = $_SERVER['REMOTE_ADDR'];


    if ($latitude === null || $longitude === null || $date === null || $ip === null) {
        $response = [
            'status' => 'error',
            'message' => 'Invalid input data'
        ];
        echo json_encode($response);
        return; 
    }

    $ss = new PositionService();
  
    $ss->create(new Position(1, $latitude, $longitude, $date, $imei, $ip)); 

    $response = [
        'status' => 'success',
        'message' => 'Position added successfully'
    ];
    
    echo json_encode($response);
}
