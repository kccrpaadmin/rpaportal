<?php
	header("Content-type: text/xml; charset=utf-8");
	
	$data = 100;
	$tempData = rand(1, 50);
	
	if ($data * 0.5 < rand(1, 100)) {
		$data = $data - $tempData;
	} else {
		$data = $data + $tempData;
	}

	// date format example : "2010/02/15 20:30:00"
	date_default_timezone_set('US/Eastern');
	$dateFmt = date('Y/m/d H:i:s', time());
	
	echo "<items>\n";
	echo "<item>\n";
	echo "<Time>" . $dateFmt . "</Time>\n";
	echo "<Data>" . $data . "</Data>\n";
	echo "</item>\n";
	echo "</items>\n";
?>