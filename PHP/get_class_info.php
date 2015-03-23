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
 
if (isset($_GET['class'])){

	$classid = $_GET['class'];
	 
	// get all staff from class table
	$result = mysql_query("SELECT class.*, module.Module_Name, staff.Forename, staff.Surname FROM class INNER JOIN module ON class.Module_Code=module.Module_Code INNER JOIN staff ON class.Staff_ID=staff.Staff_ID WHERE (Class_ID = $classid)") or die(mysql_error());
	 
	// check for empty result
	if (mysql_num_rows($result) > 0) {
		// looping through all results
		// staff node
		$response["class"] = array();
	 
		while ($row = mysql_fetch_array($result)) {
			// temp staff array
			$class = array();
			$class["Class_Type"] = $row["Class_Type"];
			$class["Module_Code"] = $row["Module_Code"];
			$class["Module_Name"] = $row["Module_Name"];
			$class["DateTime"] = $row["DateTime"];
			$class["Forename"] = $row["Forename"];
			$class["Surname"] = $row["Surname"];
	 
			// push single class into final response array
			array_push($response["class"], $class);
		}
		// success
		$response["success"] = 1;
	 
		// echoing JSON response
		echo json_encode($response);
	} else {
		// no class found
		$response["success"] = 0;
		$response["message"] = "No class found";
	 
		// echo no staff JSON
		echo json_encode($response);
	}
	
}
?>