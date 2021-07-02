<?php
	$data = "";
	$extension = "";
	$updir =  $_SERVER['SERVER_NAME'];

	if ((isset($_POST['data'])) && (strlen(trim($_POST['data'])) > 0)) {
		$data = stripslashes(strip_tags($_POST['data']));
	}

	if ((isset($_POST['extension'])) && (strlen(trim($_POST['extension'])) > 0)) {
		$extension = stripslashes(strip_tags($_POST['extension']));
	}
	
	$date = new DateTime();
	
	$userIp = $_SERVER["REMOTE_ADDR"];

	$filename = $userIp . "_" . $date->getTimestamp()  . "." . $extension;
	$filePath = "../chartTest/savedImage/" . $filename;

	$byteFile = base64_decode($data);
	file_put_contents($filePath, $byteFile);
	
	$path = $updir . "/chartTest/savedImage/" . $filename;
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="Content-Script-Type" content="text/javascript"/>
<meta http-equiv="Content-Style-Type" content="text/css"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>KoolChart for HTML5</title>
<link rel="stylesheet" type="text/css" href="http://www.koolchart.com/demo/Samples/KoolChartSample.css"/>

</head>
<body>
	<div id="header" style="text-align:center;font-family:Malgun Gothic;">Sending the image to the server is completed.</div>
	<div id="content" style="text-align:center;">
		Path : <?=$path?><br>
		<img src="http://<?=$path?>"/>
	</div>
	<div id="footer">
		<font color="#ff0000" size="4"><b>Sending the chart image to the server and saving the image file are not supported in IE7,8.<br>Refer to the HTML source for the details.</b></font>
	</div>
</body>
</html>

