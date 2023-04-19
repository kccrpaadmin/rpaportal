/*
    객체 생성 권한을 개발자에게 부여하지 않았기 때문에
    객체가 기본 속성정의와 리턴값을 가지도록 설계하였다.
    사용자 속성정의 파라미터를 받아서, 기본 속성정의 보다 우선적으로 적용되도록 처리 하였다.
*/

(function (window) {

	// 공통함수 모듈 정의
    var CommonFunc = function () { 
    	this.certInfo = {};
		this.setCertInfo = function (pCertInfo) {
			this.certInfo = pCertInfo;
        };
    }
   
    // 디바이스 타입 확인 함수
    CommonFunc.prototype.getDeviceType = function () {
        var deviceType = cmCommonFunc.getDeviceType();
    	return deviceType;
    }
    
    // 브라우저 타입 확인 함수
    CommonFunc.prototype.getBrowserType = function () {
        var browserType = cmCommonFunc.getBrowserType();
    	return browserType;
    }

    // 페이지 새로고침 함수
    CommonFunc.prototype.refreshPage = function () {
        cmCommonFunc.refreshPage();
    }
    
    // 노드 제거 함수
    CommonFunc.prototype.removeNode = function (pElmt) {
        cmCommonFunc.removeNode(pElmt);
    }

    // 자식노드 제거 함수
    CommonFunc.prototype.removeChileNode = function (pElmt) {
        cmCommonFunc.removeChileNode(pElmt);
    }
    
    // Ajax 오류 메세지 처리 함수
    CommonFunc.prototype.handleErrorMsg = function (pXhr, pStatus, pErr) {
    	cmCommonFunc.handleErrorMsg(pXhr, pStatus, pErr);
    }
    
    // 달력 생성 함수
    CommonFunc.prototype.createDatepicker = function (pElmt, pType) {
    	cmCommonFunc.createDatepicker(pElmt, pType);
    }
    
    // 문자열 변경 함수
    CommonFunc.prototype.getReplaceAll = function (pVal, pSplit, pJoin) {
    	return cmCommonFunc.getReplaceAll(pVal, pSplit, pJoin);
    }
    
    // 웹크롤링 시스템체크 캡쳐 목록 생성 함수
    CommonFunc.prototype.createSystemCheckCaptureList = function (pArea, pListDatas) {
    	cmCommonFunc.createSystemCheckCaptureList(pArea, pListDatas);
    }
    
    // 웹크롤링 메뉴 목록 생성 함수
    CommonFunc.prototype.createCrawlMenulList = function (pArea, pListDatas) {
    	cmCommonFunc.createCrawlMenulList(pArea, pListDatas);
    }
    
    // OCR 메뉴 목록 생성 함수
    CommonFunc.prototype.createOcrMenulList = function (pArea, pListDatas) {
    	cmCommonFunc.createOcrMenulList(pArea, pListDatas);
    }
    
    // BOT 메뉴 목록 생성 함수
    CommonFunc.prototype.createBotMenulList = function (pArea, pListDatas) {
    	cmCommonFunc.createBotMenulList(pArea, pListDatas);
    }

	// 절감시간 메뉴 목록 생성 함수
    CommonFunc.prototype.createResultMenulList = function (pArea, pListDatas) {
    	cmCommonFunc.createResultMenulList(pArea, pListDatas);
    }
    
    // 아이비시트 초기화 함수
    CommonFunc.prototype.initSheet = function (pSheetNm) {
    	cmCommonFunc.initSheet(pSheetNm);
    }
    
    // 문자열 변경 함수
    CommonFunc.prototype.setReplaceData = function (pInput, pSplit, pJoin) {
    	var replaceData = cmCommonFunc.setReplaceData(pInput, pSplit, pJoin);
    	return replaceData;
    }
    
    // 문자열 null 체크 함수
    CommonFunc.prototype.getCheckNullYn = function (pInput) {
    	var checkNullYn = cmCommonFunc.getCheckNullYn(pInput);
    	return checkNullYn;
    }
    
    // 홈화면 타임라인 목록 생성 함수
    CommonFunc.prototype.createHomeTimeLineList = function (pArea, pListDatas) {
		cmCommonFunc.createHomeTimeLineList(pArea, pListDatas);
    }
    
    // 비공개 영역

    // 라이브러리 공통 연관 함수
    var cmCommonFunc = {
		getDeviceType: function () {
            var platForm = navigator.platform.toLowerCase()
            var filter = "win16|win32|win64|mac";
            var returnVal = "PC";
            
            if (navigator.platform) {
	            if (filter.indexOf(navigator.platform.toLowerCase()) < 0){
	            	returnVal = "Mobile";
	        	}
            }

            return returnVal;
        },
        getBrowserType: function () {
            var agent = navigator.userAgent.toLowerCase();
            var appName = navigator.appName.toLowerCase();
            var returnVal = "Standard";

            if ((appName == "netscape" && agent.search("trident") != -1) || (agent.indexOf("msie") != -1)) {
                returnVal = "IE";
            }

            return returnVal;
        },
        refreshPage: function () {
        	window.location.reload(true);
        },
        removeNode: function (pElmt) {
            if (cmCommonFunc.getBrowserType() == "IE") {
                pElmt.parentNode.removeChild(pElmt);
            }
            else {
                pElmt.remove();
            }
        },
        removeChileNode: function (pElmt) {
            while (pElmt.hasChildNodes()) {
                pElmt.removeChild(pElmt.firstChild);
            }
        },
        handleErrorMsg: function (pXhr, pStatus, pErr) {
        	var xhr = pXhr;
        	var status = pStatus;
        	var err = pErr;
        	
        	if (xhr.status == 401) {
        		libraryFunc.createDialog("Alert", null, null, null, 250, "알림", "인증만료", null, null, null);
    		}
    		else if (xhr.status == 403) {
    			libraryFunc.createDialog("Alert", null, null, null, 250, "알림", "접근금지", null, null, null);
    		}
    		else {
    			libraryFunc.createDialog("Alert", null, null, null, 250, "알림", "오류발생", null, null, null);
    		}
        },
        createDatepicker: function (pElmt, pType) {
            // pElmt는 id 및 class 가능
            var elmt = pElmt;
            var type = pType;

            // 달력이 생성될 input 박스가 존재하는 경우
            if ($(elmt).length == 0) {
                return false;
            }

            // 년월일 달력
            if (type == "YearMonthDay") {
                $(elmt).datepicker({
                    dateFormat: "yy-mm-dd"                                                                                 // Input Display Format 변경
                    , showOtherMonths: true                                                                                 // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
                    , showMonthAfterYear: true                                                                              // 년도 먼저 나오고, 뒤에 월 표시
                    , changeYear: true                                                                                      // 콤보박스에서 년 선택 가능
                    , changeMonth: true                                                                                     // 콤보박스에서 월 선택 가능
                    , showOn: "both"                                                                                        // button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
                    , buttonImage: "/resources/imgs/button/btn_datepicker_calendar.png"                          // 버튼 이미지 경로
                    , buttonImageOnly: true                                                                                 // 기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
                    , buttonText: "달력"                                                                                    // 버튼에 마우스 갖다 댔을 때 표시되는 텍스트
                    , yearSuffix: ""                                                                                        // 달력의 년도 부분 뒤에 붙는 텍스트
                    , monthNamesShort: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"]                      // 달력의 월 부분 텍스트
                    , monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]   // 달력의 월 부분 Tooltip 텍스트
                    , dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"]                                               // 달력의 요일 부분 텍스트
                    , dayNames: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"]                      // 달력의 요일 부분 Tooltip 텍스트
                    , minDate: "-30Y"                                                                                       // 최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                    , maxDate: "+30Y"                                                                                       // 최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
                    , beforeShow: function (dateText, inst) {
                        // 달력 호출시 추가한 스타일 삭제
                        $(".custom_datepicker_style").remove();
                    }
                    , onClose: function (dateText, inst) {
                        // 아래 로직 으로 값 변경은 가능 하지만, 값의 변경이 발생 하였을때 change 이벤트가 발생하지 않음
                        // custom-func.js 아래 $.fn.valChange 함수가 Jquery change 트리거를 발생하게 함
                        $(this).valChange(new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay).format("yyyy-MM-dd"));

                        // -아래-
                        //$(this).datepicker("setDate", new Date(inst.selectedYear, inst.selectedMonth, 1));
                        //$(this).val(new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay).format('yyyy-MM-dd'));
                        //this.value = new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay).format("yyyy-MM-dd");
                    }
                });
            } // 년월 달력
            else if (type == "YearMonth") {
                $(elmt).datepicker({
                    dateFormat: "yy-mm"                                                                                    // Input Display Format 변경
                    , showOtherMonths: true                                                                                 // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
                    , showMonthAfterYear: true                                                                              // 년도 먼저 나오고, 뒤에 월 표시
                    , changeYear: true                                                                                      // 콤보박스에서 년 선택 가능
                    , changeMonth: true                                                                                     // 콤보박스에서 월 선택 가능
                    , showOn: "both"                                                                                        // button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
                	, buttonImage: "/resources/imgs/button/btn_datepicker_calendar.png"                          // 버튼 이미지 경로
                    , buttonImageOnly: true                                                                                 // 기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
                    , buttonText: "달력"                                                                                    // 버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
                    , yearSuffix: ""                                                                                        // 달력의 년도 부분 뒤에 붙는 텍스트
                    , monthNamesShort: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"]                      // 달력의 월 부분 텍스트
                    , monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]   // 달력의 월 부분 Tooltip 텍스트
                    , dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"]                                               // 달력의 요일 부분 텍스트
                    , dayNames: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"]                      // 달력의 요일 부분 Tooltip 텍스트
                    , minDate: "-30Y"                                                                                       // 최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                    , maxDate: "+30Y"                                                                                       // 최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
                    , beforeShow: function (dateText, inst) {
                        // 달력 호출시 추가한 스타일 삭제
                        $(".custom_datepicker_style").remove();

                        // 달력 호출시 스타일 추가
                        $("head").append("<style class='custom_datepicker_style' type='text/css'>.ui-datepicker-calendar{display:none;} .ui-datepicker-header{margin-bottom:2px;}</style>");

                        // 달력 팝업 년월 정의 (이미 선택 값이 있는 경우)
                        if (dateText.value.length > 0) {
                            var year = dateText.value.substr(0, 4);
                            var month = dateText.value.substr(5, 2);
                            // day : 1인 경우 - 다음월 1일, 0인 경우 당월 31일
                            $(this).datepicker("option", "defaultDate", new Date(year, month, 0));
                            $(this).datepicker("setDate", new Date(year, month, 0));
                        }
                    }
                    , onClose: function (dateText, inst) {
                        // 아래 로직 으로 값 변경은 가능 하지만, 값의 변경이 발생 하였을때 change 이벤트가 발생하지 않음
                        // custom-func.js 아래 $.fn.valChange 함수가 Jquery change 트리거를 발생하게 함
                        // day : 1인 경우 - 다음월 1일, 0인 경우 당월 31일
                        $(this).valChange(new Date(inst.selectedYear, inst.selectedMonth, 1).format("yyyy-MM"));

                        // -아래-
                        //$(this).datepicker("setDate", new Date(inst.selectedYear, inst.selectedMonth, 1));
                        //$(this).val(new Date(inst.selectedYear, inst.selectedMonth, 1).format("yyyy-MM"));
                        //this.value = new Date(inst.selectedYear, inst.selectedMonth, 1).format("yyyy-MM");
                    }
                });
            } // 년 달력
            else if (type == "Year") {
                $(elmt).datepicker({
                    dateFormat: "yy"                                                                                       // Input Display Format 변경
                    , showOtherMonths: true                                                                                 // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
                    , showMonthAfterYear: true                                                                              // 년도 먼저 나오고, 뒤에 월 표시
                    , changeYear: true                                                                                      // 콤보박스에서 년 선택 가능
                    , changeMonth: true                                                                                     // 콤보박스에서 월 선택 가능
                    , showOn: "both"                                                                                        // button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
                	, buttonImage: "/resources/imgs/button/btn_datepicker_calendar.png"                          // 버튼 이미지 경로
                    , buttonImageOnly: true                                                                                 // 기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
                    , buttonText: "달력"                                                                                    // 버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
                    , yearSuffix: ""                                                                                        // 달력의 년도 부분 뒤에 붙는 텍스트
                    , monthNamesShort: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"]                      // 달력의 월 부분 텍스트
                    , monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]   // 달력의 월 부분 Tooltip 텍스트
                    , dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"]                                               // 달력의 요일 부분 텍스트
                    , dayNames: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"]                      // 달력의 요일 부분 Tooltip 텍스트
                    , minDate: "-30Y"                                                                                       // 최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                    , maxDate: "+30Y"                                                                                       // 최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
                    , beforeShow: function (dateText, inst) {
                        // 달력 호출시 추가한 스타일 삭제
                        $(".custom_datepicker_style").remove();

                        // 달력 호출시 스타일 추가
                        $("head").append("<style class='custom_datepicker_style' type='text/css'>.ui-datepicker-month{display:none;} .ui-datepicker-prev{display:none;} .ui-datepicker-next{display:none;} .ui-datepicker-calendar{display:none;} .ui-datepicker-header{margin-bottom:2px;}</style>");

                        // 달력 팝업 년월 정의 (이미 선택 값이 있는 경우)
                        if (dateText.value.length > 0) {
                            var year = dateText.value.substr(0, 4);
                            // day : 1인 경우 - 다음월 1일, 0인 경우 당월 31일
                            $(this).datepicker("option", "defaultDate", new Date(year, 1, 0));
                            $(this).datepicker("setDate", new Date(year, 1, 0));
                        }
                    }
                    , onClose: function (dateText, inst) {
                        // 아래 로직 으로 값 변경은 가능 하지만, 값의 변경이 발생 하였을때 change 이벤트가 발생하지 않음
                        // custom-func.js 아래 $.fn.valChange 함수가 Jquery change 트리거를 발생하게 함
                        // day : 1인 경우 - 다음월 1일, 0인 경우 당월 31일
                        $(this).valChange(new Date(inst.selectedYear, 1, 0).format("yyyy"));

                        // -아래-
                        //$(this).datepicker("setDate", new Date(inst.selectedYear, inst.selectedMonth, 1));
                        //$(this).val(new Date(inst.selectedYear, inst.selectedMonth, 1).format("yyyy-MM"));
                        //this.value = new Date(inst.selectedYear, inst.selectedMonth, 1).format("yyyy-MM");
                    }
                });
            }
        },
        getReplaceAll: function (pVal, pSplit, pJoin) {
        	return pVal.split(pSplit).join(pJoin);
        },
        createSystemCheckCaptureList: function (pArea, pListDatas) {
        	var captureBox = document.getElementById(pArea);
    		var listDatas = pListDatas;
    		var listDatasLen = listDatas.length;
    		
    		for (var i = 0; i < listDatasLen; i++) {
    			var systemNm = listDatas[i].systemNm;
    			var filePath = listDatas[i].filePath;
    			var arrFilePaths = filePath.split(".");
    			var urlPath = arrFilePaths[0];
    			var fileExtenNm = arrFilePaths[1];

    			if (fileExtenNm == "png") {
    				// 소제목
    				var subTitle = document.createElement("div");
    				subTitle.className = "sub_title";
    				subTitle.innerText = systemNm;
    				captureBox.appendChild(subTitle);
            		
    				// 캡쳐 이미지
    				var captureImg = document.createElement("img");
    				captureImg.className = "capture_img";
    				captureImg.src = filePath;
    				captureBox.appendChild(captureImg);
    			}
    			else {
    				// 소제목
    				var subTitle = document.createElement("div");
    				subTitle.className = "sub_title";
    				subTitle.innerText = systemNm;
    				captureBox.appendChild(subTitle);
					
    				// 캡쳐 파일
    				var captureFile = document.createElement("a");
    				captureFile.className = "capture_file";
    				captureFile.innerText = systemNm;
    				captureFile.href = filePath;
    				captureBox.appendChild(captureFile);
    			}
    		}
        },
        createCrawlMenulList: function (pArea, pListDatas) {
        	var area = document.getElementById(pArea);
        	var listDatas = pListDatas;
        	var listDatasLen = listDatas.length;
        	
        	// 기존 목록 삭제
        	cmCommonFunc.removeChileNode(area);
        	
        	var crawlMenuListCnt = document.createElement("div");
        	crawlMenuListCnt.className = "crawl_menu_list_cnt";
        	crawlMenuListCnt.innerText = "총 " + listDatasLen + "건"
        	area.appendChild(crawlMenuListCnt);
        	
        	// 데이터가 존재 하는 경우
        	if (listDatasLen > 0) {
        		for (var i = 0; i < listDatasLen; i++) {
        			var data = listDatas[i];
        			
        			// 리스트 여러개를 감싸는 박스 
        			var crawlMenuListBox = document.createElement("div");
            		crawlMenuListBox.className = "crawl_menu_list_box";
            		area.appendChild(crawlMenuListBox);
            		
            		// 리스트 박스
            		var crawlMenuList = document.createElement("div");
            		crawlMenuList.className = "crawl_menu_list";
            		crawlMenuList.id = data.menuId;
            		crawlMenuListBox.appendChild(crawlMenuList);
            		
            		// 제목 영역
            		var crawlMenuSubject = document.createElement("div");
            		crawlMenuSubject.className = "crawl_menu_subject";
            		crawlMenuSubject.innerText = data.menuNm;
            		crawlMenuList.appendChild(crawlMenuSubject);
            		
            		// 담당부서 영역
            		var crawlMenuDept = document.createElement("div");
            		crawlMenuDept.className = "crawl_menu_dept";
            		crawlMenuList.appendChild(crawlMenuDept);
            		
            		var crawlMenuDeptTitle = document.createElement("div");
            		crawlMenuDeptTitle.className = "crawl_menu_dept_title";
            		crawlMenuDeptTitle.innerText = "담당부서";
            		crawlMenuDept.appendChild(crawlMenuDeptTitle);
            		
            		var crawlMenuDeptInfo = document.createElement("div");
            		crawlMenuDeptInfo.className = "crawl_menu_dept_info";
            		crawlMenuDeptInfo.innerText = data.deptNm;
            		crawlMenuDept.appendChild(crawlMenuDeptInfo);
            		
            		// 절감시간 영역
            		var crawlMenuWork = document.createElement("div");
            		crawlMenuWork.className = "crawl_menu_work";
            		crawlMenuList.appendChild(crawlMenuWork);
            		
            		var crawlMenuWorkTitle = document.createElement("div");
            		crawlMenuWorkTitle.className = "crawl_menu_work_title";
            		crawlMenuWorkTitle.innerText = "절감시간";
            		crawlMenuWork.appendChild(crawlMenuWorkTitle);
            		
            		var crawlMenuWorkInfo = document.createElement("div");
            		crawlMenuWorkInfo.className = "crawl_menu_work_info";
            		crawlMenuWorkInfo.innerText = data.timeTypeNm + " / " + data.runSeq + " / " + data.runTime;
            		crawlMenuWork.appendChild(crawlMenuWorkInfo);
            		
            		// 버튼영역
            		var crawlMenuBtn = document.createElement("div");
            		crawlMenuBtn.className = "crawl_menu_btn";
            		crawlMenuList.appendChild(crawlMenuBtn);
            		
            		var btnCrawlWorkInfo = document.createElement("a");
            		btnCrawlWorkInfo.className = "btn_crawl_work_info";
            		btnCrawlWorkInfo.innerText = "업무설명";
            		crawlMenuBtn.appendChild(btnCrawlWorkInfo);
            		
            		var btnCrawlRunInfo = document.createElement("a");
            		btnCrawlRunInfo.className = "btn_crawl_run_info";
            		btnCrawlRunInfo.innerText = "업무수행";
            		btnCrawlRunInfo.setAttribute("runUrl", data.runUrl);
            		crawlMenuBtn.appendChild(btnCrawlRunInfo);
            		
            		var btnCrawlManageInfo = document.createElement("a");
            		btnCrawlManageInfo.className = "btn_crawl_manage_info";
            		btnCrawlManageInfo.innerText = "업무관리";
            		btnCrawlManageInfo.setAttribute("manageUrl", data.manageUrl);
            		crawlMenuBtn.appendChild(btnCrawlManageInfo);
    			}
    		}
        },          
        createOcrMenulList: function (pArea, pListDatas) {
        	var area = document.getElementById(pArea);
        	var listDatas = pListDatas;
        	var listDatasLen = listDatas.length;
        	
        	// 기존 목록 삭제
        	cmCommonFunc.removeChileNode(area);
        	
        	var ocrMenuListCnt = document.createElement("div");
        	ocrMenuListCnt.className = "ocr_menu_list_cnt";
        	ocrMenuListCnt.innerText = "총 " + listDatasLen + "건"
        	area.appendChild(ocrMenuListCnt);
        	
        	// 데이터가 존재 하는 경우
        	if (listDatasLen > 0) {
        		for (var i = 0; i < listDatasLen; i++) {
        			var data = listDatas[i];
        			
        			// 리스트 여러개를 감싸는 박스 
        			var ocrMenuListBox = document.createElement("div");
            		ocrMenuListBox.className = "ocr_menu_list_box";
            		area.appendChild(ocrMenuListBox);
            		
            		// 리스트 박스
            		var ocrMenuList = document.createElement("div");
            		ocrMenuList.className = "ocr_menu_list";
            		ocrMenuList.id = data.menuId;
            		ocrMenuListBox.appendChild(ocrMenuList);
            		
            		// 제목 영역
            		var ocrMenuSubject = document.createElement("div");
            		ocrMenuSubject.className = "ocr_menu_subject";
            		ocrMenuSubject.innerText = data.menuNm;
            		ocrMenuList.appendChild(ocrMenuSubject);
            		
            		// 담당부서 영역
            		var ocrMenuDept = document.createElement("div");
            		ocrMenuDept.className = "ocr_menu_dept";
            		ocrMenuList.appendChild(ocrMenuDept);
            		
            		var ocrMenuDeptTitle = document.createElement("div");
            		ocrMenuDeptTitle.className = "ocr_menu_dept_title";
            		ocrMenuDeptTitle.innerText = "담당부서";
            		ocrMenuDept.appendChild(ocrMenuDeptTitle);
            		
            		var ocrMenuDeptInfo = document.createElement("div");
            		ocrMenuDeptInfo.className = "ocr_menu_dept_info";
            		ocrMenuDeptInfo.innerText = data.deptNm;
            		ocrMenuDept.appendChild(ocrMenuDeptInfo);
            		
            		// 기능설명 영역
            		var ocrMenuWork = document.createElement("div");
            		ocrMenuWork.className = "ocr_menu_work";
            		ocrMenuList.appendChild(ocrMenuWork);
            		
            		var ocrMenuWorkTitle = document.createElement("div");
            		ocrMenuWorkTitle.className = "ocr_menu_work_title";
            		ocrMenuWorkTitle.innerText = "기능설명";
            		ocrMenuWork.appendChild(ocrMenuWorkTitle);
            		
            		var ocrMenuWorkInfo = document.createElement("div");
            		ocrMenuWorkInfo.className = "ocr_menu_work_info";
            		ocrMenuWorkInfo.innerText = "편의기능";
            		ocrMenuWork.appendChild(ocrMenuWorkInfo);
            		
            		// 버튼영역
            		var ocrMenuBtn = document.createElement("div");
            		ocrMenuBtn.className = "ocr_menu_btn";
            		ocrMenuList.appendChild(ocrMenuBtn);
            		
            		var btnOcrWorkInfo = document.createElement("a");
            		btnOcrWorkInfo.className = "btn_ocr_work_info";
            		btnOcrWorkInfo.innerText = "업무설명";
            		ocrMenuBtn.appendChild(btnOcrWorkInfo);
            		
            		var btnOcrRunInfo = document.createElement("a");
            		btnOcrRunInfo.className = "btn_ocr_run_info";
            		btnOcrRunInfo.innerText = "업무수행";
            		btnOcrRunInfo.setAttribute("runUrl", data.runUrl);
            		ocrMenuBtn.appendChild(btnOcrRunInfo);
    			}
    		}
        },        
        createBotMenulList: function (pArea, pListDatas) {
        	var area = document.getElementById(pArea);
        	var listDatas = pListDatas;
        	var listDatasLen = listDatas.length;
        	
        	// 기존 목록 삭제
        	cmCommonFunc.removeChileNode(area);
        	
        	var botMenuListCnt = document.createElement("div");
        	botMenuListCnt.className = "bot_menu_list_cnt";
        	botMenuListCnt.innerText = "총 " + listDatasLen + "건"
        	area.appendChild(botMenuListCnt);
        	
        	// 데이터가 존재 하는 경우
        	if (listDatasLen > 0) {
        		for (var i = 0; i < listDatasLen; i++) {
        			var data = listDatas[i];
        			
        			// 리스트 여러개를 감싸는 박스 
        			var botMenuListBox = document.createElement("div");
            		botMenuListBox.className = "bot_menu_list_box";
            		area.appendChild(botMenuListBox);
            		
            		// 리스트 박스
            		var botMenuList = document.createElement("div");
            		botMenuList.className = "bot_menu_list";
            		botMenuList.id = data.menuId;
            		botMenuListBox.appendChild(botMenuList);
            		
            		// 제목 영역
            		var botMenuSubject = document.createElement("div");
            		botMenuSubject.className = "bot_menu_subject";
            		botMenuSubject.innerText = data.menuNm;
            		botMenuList.appendChild(botMenuSubject);
            		
            		// 담당부서 영역
            		var botMenuDept = document.createElement("div");
            		botMenuDept.className = "bot_menu_dept";
            		botMenuList.appendChild(botMenuDept);
            		
            		var botMenuDeptTitle = document.createElement("div");
            		botMenuDeptTitle.className = "bot_menu_dept_title";
            		botMenuDeptTitle.innerText = "담당부서";
            		botMenuDept.appendChild(botMenuDeptTitle);
            		
            		var botMenuDeptInfo = document.createElement("div");
            		botMenuDeptInfo.className = "bot_menu_dept_info";
            		botMenuDeptInfo.innerText = data.deptNm;
            		botMenuDept.appendChild(botMenuDeptInfo);
            		
            		// 절감시간 영역
            		var botMenuWork = document.createElement("div");
            		botMenuWork.className = "bot_menu_work";
            		botMenuList.appendChild(botMenuWork);
            		
            		var botMenuWorkTitle = document.createElement("div");
            		botMenuWorkTitle.className = "bot_menu_work_title";
            		botMenuWorkTitle.innerText = "절감시간";
            		botMenuWork.appendChild(botMenuWorkTitle);
            		
            		var botMenuWorkInfo = document.createElement("div");
            		botMenuWorkInfo.className = "bot_menu_work_info";
            		botMenuWorkInfo.innerText = data.timeTypeNm + " / " + data.runSeq + " / " + data.runTime;
            		botMenuWork.appendChild(botMenuWorkInfo);
            		
            		// 버튼영역
            		var botMenuBtn = document.createElement("div");
            		botMenuBtn.className = "bot_menu_btn";
            		botMenuList.appendChild(botMenuBtn);
            		
            		var btnBotWorkInfo = document.createElement("a");
            		btnBotWorkInfo.className = "btn_bot_work_info";
            		btnBotWorkInfo.innerText = "업무설명";
            		botMenuBtn.appendChild(btnBotWorkInfo);
            		
            		var btnBotRunInfo = document.createElement("a");
            		btnBotRunInfo.className = "btn_bot_run_info";
            		btnBotRunInfo.innerText = "업무수행";
            		btnBotRunInfo.setAttribute("runUrl", data.runUrl);
            		botMenuBtn.appendChild(btnBotRunInfo);
            		
            		var btnBotManageInfo = document.createElement("a");
            		btnBotManageInfo.className = "btn_bot_manage_info";
            		btnBotManageInfo.innerText = "업무관리";
            		btnBotManageInfo.setAttribute("manageUrl", data.manageUrl);
            		botMenuBtn.appendChild(btnBotManageInfo);
    			}
    		}
        },        
        createResultMenulList: function (pArea, pListDatas) {
        	var area = document.getElementById(pArea);
        	var listDatas = pListDatas;
        	var listDatasLen = listDatas.length;
        	
        	// 기존 목록 삭제
        	cmCommonFunc.removeChileNode(area);
        	
        	var botMenuListCnt = document.createElement("div");
        	botMenuListCnt.className = "menu_list_cnt";
        	botMenuListCnt.innerText = "총 " + listDatasLen + "건"
        	area.appendChild(botMenuListCnt);
        	
        	// 데이터가 존재 하는 경우
        	if (listDatasLen > 0) {
        		for (var i = 0; i < listDatasLen; i++) {
        			var data = listDatas[i];
        			
        			// 리스트 여러개를 감싸는 박스 
        			var botMenuListBox = document.createElement("div");
            		botMenuListBox.className = "menu_list_box";
            		area.appendChild(botMenuListBox);
            		
            		// 리스트 박스
            		var botMenuList = document.createElement("div");
            		botMenuList.className = "menu_list";
            		botMenuList.id = data.menuId;
            		botMenuListBox.appendChild(botMenuList);
            		
            		// 제목 영역
            		var botMenuSubject = document.createElement("div");
            		botMenuSubject.className = "menu_subject";
            		botMenuSubject.innerText = data.menuNm;
            		botMenuList.appendChild(botMenuSubject);
            		
            		// 담당부서 영역
            		var botMenuDept = document.createElement("div");
            		botMenuDept.className = "menu_dept";
            		botMenuList.appendChild(botMenuDept);
            		
            		var botMenuDeptTitle = document.createElement("div");
            		botMenuDeptTitle.className = "menu_dept_title";
            		botMenuDeptTitle.innerText = "담당부서";
            		botMenuDept.appendChild(botMenuDeptTitle);
            		
            		var botMenuDeptInfo = document.createElement("div");
            		botMenuDeptInfo.className = "menu_dept_info";
            		botMenuDeptInfo.innerText = data.deptNm;
            		botMenuDept.appendChild(botMenuDeptInfo);
            		
            		// 절감시간 영역
            		var botMenuWork = document.createElement("div");
            		botMenuWork.className = "menu_work";
            		botMenuList.appendChild(botMenuWork);
            		
            		var botMenuWorkTitle = document.createElement("div");
            		botMenuWorkTitle.className = "menu_work_title";
            		botMenuWorkTitle.innerText = "누계 절감시간";
            		botMenuWork.appendChild(botMenuWorkTitle);
            		
            		var botMenuWorkInfo = document.createElement("div");
            		botMenuWorkInfo.className = "menu_work_info";
            		botMenuWorkInfo.innerText = data.runCnt + " / " + data.totTime;
            		botMenuWork.appendChild(botMenuWorkInfo);
            		
            		// 버튼영역
            		var botMenuBtn = document.createElement("div");
            		botMenuBtn.className = "menu_btn";
            		botMenuList.appendChild(botMenuBtn);
            		
            		var btnBotWorkInfo = document.createElement("a");
            		btnBotWorkInfo.className = "btn_work_info";
            		btnBotWorkInfo.innerText = "업무설명";
            		botMenuBtn.appendChild(btnBotWorkInfo);
            		
    			}
    		}
        },        
        initSheet: function (pSheetNm) {
        	if (Grids != undefined) {
	        	for (var i = 0; i < Grids.length; i++) {
	    			if (Grids[i].id == pSheetNm) {
	    				eval(pSheetNm + ".Reset()");
	    	        }
	    		}
        	}
        },
        setReplaceData: function (pInput, pSplit, pJoin) {
        	return pInput.split(pSplit).join(pJoin);
        },
        getCheckNullYn: function (pInput) {
        	var checkNullYn = "N";
        	
        	if (typeof pInput == "undefined" || pInput == null || pInput == "") {
        		checkNullYn = "Y";
        	}
        	
        	return checkNullYn;
        },
        createHomeTimeLineList: function (pArea, pListDatas) {
        	var area = document.getElementById(pArea);
        	var listDatas = pListDatas;
        	var listDatasLen = listDatas.length;
        	
        	// 기존 목록 삭제
        	cmCommonFunc.removeChileNode(area);

        	// 데이터가 존재 하는 경우
        	if (listDatasLen > 0) {
        		for (var i = 0; i < listDatasLen; i++) {
        			var data = listDatas[i];
        			
        			// 타임라인 박스 
        			var homeTimeLine = document.createElement("div");
        			homeTimeLine.className = "home_time_line";
        			area.appendChild(homeTimeLine);
        			
        			// 왼쪽 박스
            		var homeTimeLineLeft = document.createElement("div");
            		homeTimeLineLeft.className = "home_time_line_left";
            		homeTimeLine.appendChild(homeTimeLineLeft);

            		// 중간 박스
            		var homeTimeLineCenter = document.createElement("div");
            		homeTimeLineCenter.className = "home_time_line_center";
            		homeTimeLineCenter.innerText = data.startTime;
            		homeTimeLine.appendChild(homeTimeLineCenter);

            		// 오른쪽 박스
            		var homeTimeLineRight = document.createElement("div");
            		homeTimeLineRight.className = "home_time_line_right";
            		homeTimeLineRight.innerText = data.menuNm;
            		homeTimeLine.appendChild(homeTimeLineRight);
            		
            		// 상태 박스
        			var homeTimeLineStatus = document.createElement("div");
            		homeTimeLineStatus.innerText = data.statusType;
            		if (data.statusType == "실행") {
            			homeTimeLineStatus.className = "home_time_line_status_r";	
            		}
            		else if (data.statusType == "대기") {
            			homeTimeLineStatus.className = "home_time_line_status_q";
            		}
            		else {
            			homeTimeLineStatus.className = "home_time_line_status_s";
            		}
            		homeTimeLineLeft.appendChild(homeTimeLineStatus);
    			}
    		}
        }
    }
    
    window.CommonFunc = CommonFunc;
})(window);