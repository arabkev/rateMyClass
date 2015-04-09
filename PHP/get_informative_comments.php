<?php header('Access-Control-Allow-Origin: *');

  // include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();



if (isset($_GET["class"])){
  $class = $_GET["class"];

  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Informative_Comment = 'No Comment'";
  
  $result = mysql_query($sql_query) or die(mysql_error());
  echo "{ \"cols\": [ {\"id\":\"\",\"label\":\"No Comment\",\"pattern\":\"\",\"type\":\"string\"}, {\"id\":\"\",\"label\":\"Number\",\"pattern\":\"\",\"type\":\"number\"}, {\"type\":\"string\", \"role\":\"style\"} ], \"rows\": [ ";
  
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"No Comment\",\"f\":\"No Comment\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"red\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Informative_Comment = 'No new information given'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"No new information given\",\"f\":\"No new information given\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"green\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Informative_Comment = 'Lack of information'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lack of information\",\"f\":\"Lack of information\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"gold\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Informative_Comment = 'Provided with new information'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Provided with new information\",\"f\":\"Provided with new information\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"blue\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Informative_Comment = 'Lots of information'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lots of information\",\"f\":\"Lots of information\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"pink\",\"f\":null}]},";
  }
  
  echo " ] }";
}
?>
