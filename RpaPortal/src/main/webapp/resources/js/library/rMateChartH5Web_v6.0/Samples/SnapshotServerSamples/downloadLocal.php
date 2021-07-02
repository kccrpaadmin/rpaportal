<?php
	$date = new DateTime();
	
	$data = "";
	$fileName = "";
	$extension = "";
	$updir = "";
	$userIp = "";
	
	if ((isset($_POST['data'])) && (strlen(trim($_POST['data'])) > 0)) {
		$data = stripslashes(strip_tags($_POST['data']));
	}
	if ((isset($_POST['fileName'])) && (strlen(trim($_POST['fileName'])) > 0)) {
		$fileName = stripslashes(strip_tags($_POST['fileName']));
	}
	if ((isset($_POST['extension'])) && (strlen(trim($_POST['extension'])) > 0)) {
		$extension = stripslashes(strip_tags($_POST['extension']));
	}
	
	$userIp = $_SERVER["REMOTE_ADDR"];
	
	$filename = $userIp . "_" . $date->getTimestamp()  . "." . $extension;
	$filePath = "../chartTest/savedImage/" . $filename;
	
	if ($extension == "csv")
		file_put_contents($filePath, $data);
	else
		file_put_contents($filePath, base64_decode($data));
	
	if ($fd = fopen ($filePath, "r")) {
		$fsize = filesize($filePath);
		
		header("Content-type: application/" . $extension);
		header("Content-Disposition: attachment; filename=\"".$filePath."\"");
		
		header("Content-length: $fsize");
		
		while(!feof($fd)) {
			$buffer = fread($fd, 2048);
			echo $buffer;
		}
	}
	
	fclose ($fd);
	exit;
?>

