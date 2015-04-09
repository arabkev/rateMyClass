<?php
 
/*
 * Following code will create a new feedback row
 * All feedback details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['Class_ID']) && isset($_POST['Email'])) {
 
    $class = $_POST['Class_ID'];
	$email = $_POST['Email'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("SELECT AVG(Interesting_Rating) as avg_interesting, AVG(Interactive_Rating) as avg_interactive, AVG(Informative_Rating) as avg_informative, AVG(Intelligible_Rating) as avg_intelligible, AVG(Innovative_Rating) as avg_innovative FROM feedback WHERE (Class_ID = $class)");
 
    while($row = mysql_fetch_array($result)){
		$msg = "You have received feedback on a class:\n\n\nAVERAGE RATINGS\n\nInteresting: " . $row['avg_interesting'] . "\nInformative: " . $row['avg_informative'] . "\nInteractive: " . $row['avg_interactive'] . "\nIntelligible: " . $row['avg_intelligible'] . "\nInnovative: " . $row['avg_innovative'];
		mail($email, "New Class Feedback", $msg);
	}
	
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>