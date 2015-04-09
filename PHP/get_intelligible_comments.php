<?php header('Access-Control-Allow-Origin: *');

  // include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();



if (isset($_GET["class"])){
  $class = $_GET["class"];

  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Intelligible_Comment = 'No Comment'";
  
  $result = mysql_query($sql_query) or die(mysql_error());
  echo "{ \"cols\": [ {\"id\":\"\",\"label\":\"No Comment\",\"pattern\":\"\",\"type\":\"string\"}, {\"id\":\"\",\"label\":\"Number\",\"pattern\":\"\",\"type\":\"number\"}, {\"type\":\"string\", \"role\":\"style\"} ], \"rows\": [ ";
  
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"No Comment\",\"f\":\"No Comment\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"red\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Intelligible_Comment = 'Lecturer had a voice/accent that could not be understood'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lecturer had a voice/accent that could not be understood\",\"f\":\"Lecturer had a voice/accent that could not be understood\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"green\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Intelligible_Comment = 'Information given was vague and unclear'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Information given was vague and unclear\",\"f\":\"Information given was vague and unclear\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"gold\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Intelligible_Comment = 'Lecturer spoke clearly'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lecturer spoke clearly\",\"f\":\"Lecturer spoke clearly\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"blue\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Intelligible_Comment = 'Information was given in a clear way'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Information was given in a clear way\",\"f\":\"Information was given in a clear way\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"pink\",\"f\":null}]},";
  }
  
  echo " ] }";
}
?>
