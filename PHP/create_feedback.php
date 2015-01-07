<?php
 
/*
 * Following code will create a new feedback row
 * All feedback details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['Class_ID']) && isset($_POST['Interesting_Rating']) && isset($_POST['Interesting_Comment']) && isset($_POST['Informative_Rating']) && isset($_POST['Informative_Comment']) && isset($_POST['Interactive_Rating']) && isset($_POST['Interactive_Comment']) && isset($_POST['Intelligible_Rating']) && isset($_POST['Intelligible_Comment']) && isset($_POST['Innovative_Rating']) && isset($_POST['Innovative_Comment'])) {
 
    $classid = $_POST['Class_ID'];
    $interestingRating = $_POST['Interesting_Rating'];
    $interestingComment = $_POST['Interesting_Comment'];
	$informativeRating = $_POST['Informative_Rating'];
	$informativeComment = $_POST['Informative_Comment'];
	$interactiveRating = $_POST['Interactive_Rating'];
	$interactiveComment = $_POST['Interactive_Comment'];
	$intelligibleRating = $_POST['Intelligible_Rating'];
	$intelligibleComment = $_POST['Intelligible_Comment'];
	$innovativeRating = $_POST['Innovative_Rating'];
	$innovativeComment = $_POST['Innovative_Comment'];
	
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO feedback(Class_ID, Interesting_Rating, Interesting_Comment, Informative_Rating, Informative_Comment, Interactive_Rating, Interactive_Comment, Intelligible_Rating, Intelligible_Comment, Innovative_Rating, Innovative_Comment) VALUES('$classid', '$interestingRating', '$interestingComment', '$informativeRating', '$informativeComment', '$interactiveRating', '$interactiveComment', '$intelligibleRating', '$intelligibleComment', '$innovativeRating', '$innovativeComment')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Feedback successfully posted.";
 
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