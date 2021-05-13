<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
    <meta http-equiv="Contents-Type" content="text/html" charset="utf-8" />
    <link rel="shortcut icon" type="text/css" href="/resources/imgs/favicon/kccenc.ico" />
    <link rel="stylesheet" type="text/css" href="/resources/css/font/nanumsquare.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/common.css?ver=0.1" />
    <link rel="stylesheet" type="text/css" href="/resources/js/library/seeddialog/seeddialog.min.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/library/seedmodal/seedmodal.min.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/library/seedprogress/seedprogress.min.css" />
    <link rel="stylesheet" type="text/css" href="/resources/js/jquery/jquery-ui-1.12.1/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/js/library/rMateChartH5Web_v6.0/rMateChartH5/Assets/Css/rMateChartH5.css"/>

	<script type="text/javascript" src="/resources/js/jquery/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/resources/js/library/seeddialog/seeddialog.min.js"></script>
	<script type="text/javascript" src="/resources/js/library/seedmodal/seedmodal.min.js"></script>
	<script type="text/javascript" src="/resources/js/library/seedprogress/seedprogress.min.js"></script>
	<script type="text/javascript" src="/resources/js/custom-func.js"></script>
	<script type="text/javascript" src="/resources/js/library-func.js"></script>
	<script type="text/javascript" src="/resources/js/common-func.js"></script>
	
	<script type="text/javascript" src="/resources/js/library/rMateChartH5Web_v6.0/LicenseKey/rMateChartH5License.js"></script>  
	<script type="text/javascript" src="/resources/js/library/rMateChartH5Web_v6.0/rMateChartH5/JS/rMateChartH5.js"></script>
	<script type="text/javascript" src="/resources/js/library/ibsheet/dev/ibleaders.js"></script>
    <script type="text/javascript" src="/resources/js/library/ibsheet/dev/ibsheetinfo.js"></script>
    <script type="text/javascript" src="/resources/js/library/ibsheet/dev/ibsheet.js"></script>
</head>
<body>
	<script type="text/javascript">
		
		var libraryFunc;
		var commonFunc;
		
		// 페이지 로드
	    $(document).ready(function (e) {
	        libraryFunc = new LibraryFunc();
	        commonFunc = new CommonFunc();
	    });
		
	</script>
	<tiles:insertAttribute name="body" />
</body>
</html>