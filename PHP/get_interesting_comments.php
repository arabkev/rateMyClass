<?php header('Access-Control-Allow-Origin: *');

  // include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();



if (isset($_GET["class"])){
  $class = $_GET["class"];

  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interesting_Comment = 'No Comment'";
  
  $result = mysql_query($sql_query) or die(mysql_error());
  echo "{ \"cols\": [ {\"id\":\"\",\"label\":\"No Comment\",\"pattern\":\"\",\"type\":\"string\"}, {\"id\":\"\",\"label\":\"Number\",\"pattern\":\"\",\"type\":\"number\"}, {\"type\":\"string\", \"role\":\"style\"} ], \"rows\": [ ";
  
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"No Comment\",\"f\":\"No Comment\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"red\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interesting_Comment = 'Lecturer did not seem interested'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lecturer did not seem interested\",\"f\":\"Lecturer did not seem interested\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"green\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interesting_Comment = 'Delivery was monotone'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Delivery was monotone\",\"f\":\"Delivery was monotone\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"gold\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interesting_Comment = 'Lecturer was enthusiastic'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lecturer was enthusiastic\",\"f\":\"Lecturer was enthusiastic\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"blue\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interesting_Comment = 'Delivery was lively'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Delivery was lively\",\"f\":\"Delivery was lively\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"pink\",\"f\":null}]},";
  }
  
  echo " ] }";
}
?>
