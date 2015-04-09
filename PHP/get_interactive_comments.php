<?php header('Access-Control-Allow-Origin: *');

  // include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();



if (isset($_GET["class"])){
  $class = $_GET["class"];

  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interactive_Comment = 'No Comment'";
  
  $result = mysql_query($sql_query) or die(mysql_error());
  echo "{ \"cols\": [ {\"id\":\"\",\"label\":\"No Comment\",\"pattern\":\"\",\"type\":\"string\"}, {\"id\":\"\",\"label\":\"Number\",\"pattern\":\"\",\"type\":\"number\"}, {\"type\":\"string\", \"role\":\"style\"} ], \"rows\": [ ";
  
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"No Comment\",\"f\":\"No Comment\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"red\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interactive_Comment = 'Lecturer never asked questions'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lecturer never asked questions\",\"f\":\"Lecturer never asked questions\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"green\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interactive_Comment = 'No student input'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"No student input\",\"f\":\"No student input\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"gold\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interactive_Comment = 'Lecturer asked questions'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lecturer asked questions\",\"f\":\"Lecturer asked questions\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"blue\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Interactive_Comment = 'Student input was regular and encouraged'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Student input was regular and encouraged\",\"f\":\"Student input was regular and encouraged\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"pink\",\"f\":null}]},";
  }
  
  echo " ] }";
}
?>
