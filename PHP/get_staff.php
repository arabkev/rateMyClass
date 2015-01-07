<?php
 
/*
 * Following code will list all the staff
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all staff from staff table
$result = mysql_query("SELECT * FROM staff") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // staff node
    $response["staff"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp staff array
        $staff = array();
        $staff["Staff_ID"] = $row["Staff_ID"];
        $staff["Forename"] = $row["Forename"];
        $staff["Surname"] = $row["Surname"];
        $staff["Email_Address"] = $row["Email_Address"];
        $staff["Role"] = $row["Role"];
 
        // push single staff member into final response array
        array_push($response["staff"], $staff);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no staff found
    $response["success"] = 0;
    $response["message"] = "No staff found";
 
    // echo no staff JSON
    echo json_encode($response);
}
?>