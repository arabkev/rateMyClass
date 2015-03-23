<?php
 
/*
 * Following code will create a new class row
 * All class details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['Class_Type']) && isset($_POST['Module_Code']) && isset($_POST['Staff_ID'])) {
 
    $type = $_POST['Class_Type'];
    $module = $_POST['Module_Code'];
    $staff = $_POST['Staff_ID'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO class(Class_Type, Module_Code, Staff_ID, DateTime) VALUES('$type', '$module', '$staff', CURRENT_TIMESTAMP)");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Class successfully created.";
        $response["id"] = mysql_insert_id();
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>