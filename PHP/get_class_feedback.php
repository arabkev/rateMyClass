<?php header('Access-Control-Allow-Origin: *');

  // include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();



if (isset($_GET["class"])){
  $class = $_GET["class"];

  $sql_query = "SELECT AVG(Interesting_Rating) as avg_interesting, AVG(Interactive_Rating) as avg_interactive, AVG(Informative_Rating) as avg_informative, AVG(Intelligible_Rating) as avg_intelligible, AVG(Innovative_Rating) as avg_innovative FROM feedback WHERE (Class_ID = $class)";
  
  $result = mysql_query($sql_query) or die(mysql_error());
  echo "{ \"cols\": [ {\"id\":\"\",\"label\":\"Category\",\"pattern\":\"\",\"type\":\"string\"}, {\"id\":\"\",\"label\":\"Score\",\"pattern\":\"\",\"type\":\"number\"}, {\"type\":\"string\", \"role\":\"style\"} ], \"rows\": [ ";
  
  while($row = mysql_fetch_array($result)){
    
      echo "{\"c\":[{\"v\":\"Interesting\",\"f\":\"Interesting\"},{\"v\":" . $row['avg_interesting'] . ",\"f\":null},{\"v\":\"red\",\"f\":null}]},";
	  echo "{\"c\":[{\"v\":\"Informative\",\"f\":null},{\"v\":" . $row['avg_informative'] . ",\"f\":null},{\"v\":\"green\",\"f\":null}]},";
	  echo "{\"c\":[{\"v\":\"Interactive\",\"f\":null},{\"v\":" . $row['avg_interactive'] . ",\"f\":null},{\"v\":\"gold\",\"f\":null}]},";
	  echo "{\"c\":[{\"v\":\"Intelligible\",\"f\":null},{\"v\":" . $row['avg_intelligible'] . ",\"f\":null},{\"v\":\"blue\",\"f\":null}]},";
	  echo "{\"c\":[{\"v\":\"Innovative\",\"f\":null},{\"v\":" . $row['avg_innovative'] . ",\"f\":null},{\"v\":\"pink\",\"f\":null}]}";
  }
  
  echo " ] }";
}
?>