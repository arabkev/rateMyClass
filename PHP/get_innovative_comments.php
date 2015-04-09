<?php header('Access-Control-Allow-Origin: *');

  // include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();



if (isset($_GET["class"])){
  $class = $_GET["class"];

  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Innovative_Comment = 'No Comment'";
  
  $result = mysql_query($sql_query) or die(mysql_error());
  echo "{ \"cols\": [ {\"id\":\"\",\"label\":\"No Comment\",\"pattern\":\"\",\"type\":\"string\"}, {\"id\":\"\",\"label\":\"Number\",\"pattern\":\"\",\"type\":\"number\"}, {\"type\":\"string\", \"role\":\"style\"} ], \"rows\": [ ";
  
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"No Comment\",\"f\":\"No Comment\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"red\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Innovative_Comment = 'Class felt like going through the motions'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Class felt like going through the motions\",\"f\":\"Class felt like going through the motions\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"green\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Innovative_Comment = 'Lecturer stuck to slides'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lecturer stuck to slides\",\"f\":\"Lecturer stuck to slides\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"gold\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Innovative_Comment = 'Class felt fresh and exciting'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Class felt fresh and exciting\",\"f\":\"Class felt fresh and exciting\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"blue\",\"f\":null}]},";
  }
  
  $sql_query = "SELECT COUNT(*) as cnt FROM feedback WHERE Class_ID = $class AND Innovative_Comment = 'Lecturer tried new, different teaching techniques'";
  $result = mysql_query($sql_query) or die(mysql_error());
  while($row = mysql_fetch_array($result)){
      echo "{\"c\":[{\"v\":\"Lecturer tried new, different teaching techniques\",\"f\":\"Lecturer tried new, different teaching techniques\"},{\"v\":" . $row['cnt'] . ",\"f\":null},{\"v\":\"pink\",\"f\":null}]},";
  }
  
  echo " ] }";
}
?>
