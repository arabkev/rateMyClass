<?php
 
/*
 * Following code will list all the modules
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all modules from modules table
$result = mysql_query("SELECT * FROM module") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // modules node
    $response["modules"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp module array
        $module = array();
        $module["Module_Code"] = $row["Module_Code"];
        $module["Module_Name"] = $row["Module_Name"];
 
        // push single module into final response array
        array_push($response["modules"], $module);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no modules found
    $response["success"] = 0;
    $response["message"] = "No modules found";
 
    // echo no modules JSON
    echo json_encode($response);
}
?>