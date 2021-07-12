/*
    객체 생성 권한을 개발자에게 부여하지 않았기 때문에
    객체가 기본 속성정의와 리턴값을 가지도록 설계하였다.
    사용자 속성정의 파라미터를 받아서, 기본 속성정의 보다 우선적으로 적용되도록 처리 하였다.
*/

(function (window) {

	// 라이브러리 모듈 정의
	var LibraryFunc = function () {}

    // 대화상자 객체 생성
    var seedDialog = new SeedDialog();

    // 모달팝업 객체 생성
    var seedModal = new SeedModal();
    
    // 프로그레스 객체 생성
    var seedProgress = new SeedProgress();
    
    // 대화상자 생성 함수
    LibraryFunc.prototype.createDialog = function (pType, pTheme, pTop, pLeft, pWidth, pTitle, pMsg, pPromptList, callback) {
        lfSkillFunc.createDialog(pType, pTheme, pTop, pLeft, pWidth, pTitle, pMsg, pPromptList, callback);
    }
	
	// 대화상자 닫기 함수
    LibraryFunc.prototype.closeDialog = function () {
        lfSkillFunc.closeDialog();
    }

    // 모달팝업 생성 함수
    LibraryFunc.prototype.createModal = function (pTheme, pTop, pLeft, pWidth, pHeight, pTitle, pUrl) {
    	lfSkillFunc.createModal(pTheme, pTop, pLeft, pWidth, pHeight, pTitle, pUrl);
    }
   
   	// 모달팝업 닫기 함수
    LibraryFunc.prototype.closeModal = function () {
        lfSkillFunc.closeModal();
    }
    
    // 프로그레스 생성 함수
    LibraryFunc.prototype.createProgress = function () {
        lfSkillFunc.createProgress();
    }
    
    // 프로그레스 닫기 함수
    LibraryFunc.prototype.closeProgress = function () {
        lfSkillFunc.closeProgress();
    }
    
    // 비공개 영역
    
    // 라이브러리 기능 연관 함수
    var lfSkillFunc = {
		createDialog: function (pType, pTheme, pTop, pLeft, pWidth, pTitle, pMsg, pPromptList, callback) {
            seedDialog.setConfig({
                sdTheme: "sd_theme10",		// 테마 (sd_theme1 ~ 10)
                sdTop: "center",        			// 위치(Top)
                sdLeft: "middle",       			// 위치(Left)
                sdWidth: "250",         		// 너비
                sdTitle: "대화상자"     			// 제목
            });

            // 사용자 정의
            var userConfig = {
                sdTheme: pTheme == null ? undefined : pTheme,		// 테마 (sd_theme1 ~ 10)
                sdTop: pTop == null ? undefined : pTop,                // 위치(Top)
                sdLeft: pLeft == null ? undefined : pLeft,                	// 위치(Left)
                sdWidth: pWidth == null ? undefined : pWidth,        // 너비
                sdTitle: pTitle == null ? undefined : pTitle,              // 제목
                sdMsg: pMsg                                                 	// 메세지 (필수값)
            }

            if (pType == "Alert") {
                // 버튼 목록 (필수값)
                userConfig.sdBtnList = [
                    { btnKey: "o", btnNm: "확인" }
                ]
            }
            else if (pType == "Confirm") {
                // 버튼 목록 (필수값)
                userConfig.sdBtnList = [
                    { btnKey: "o", btnNm: "확인" },
                    { btnKey: "x", btnNm: "취소" }
                ]
            }
            else if (pType == "Prompt") {
                // 버튼 목록 (필수값)
                userConfig.sdBtnList = [
                    { btnKey: "o", btnNm: "확인" },
                    { btnKey: "x", btnNm: "취소" }
                ]
                // 프롬프트 목록 (필수값)
                userConfig.sdPromptList = (pPromptList == null ? undefined : pPromptList);
            }

            seedDialog.dialog(
                seedDialog,
                pType,
                userConfig,
                callback
            );
        },
        closeDialog: function () {
            seedDialog.close();
        },
        createModal: function (pTheme, pTop, pLeft, pWidth, pHeight, pTitle, pUrl) {
            seedModal.setConfig({
                smTheme: "sm_theme10",		// 테마 (sm_theme1 ~ 10)
                smTop: "center",        		// 위치(Top) 
                smLeft: "middle",       		// 위치(Left) 
                smWidth: "1000",        		// 너비
                smHeight: "800",        		// 높이 
                smTitle: "모달팝업",    			// 제목
                smUrl: ""               			// Url
            });

            var userConfig = {}

            userConfig = {
                smTheme: pTheme == null ? undefined : pTheme,			// 테마 (sm_theme1 ~ 10)
                smTop: pTop == null ? undefined : pTop,                   // 위치(Top)
                smLeft: pLeft == null ? undefined : pLeft,                  	// 위치(Left)
                smWidth: pWidth == null ? undefined : pWidth,            // 너비
                smHeight: pHeight == null ? undefined : pHeight,         // 높이
                smTitle: pTitle == null ? undefined : pTitle,               	// 제목
                smUrl: pUrl == null ? undefined : pUrl                      	// Url
            }
            
            seedModal.modal(
                seedModal,
                userConfig
            );
        },
        closeModal: function () {
            seedModal.close();
        },
        createProgress: function () {
        	seedProgress.progress(seedProgress);
        },
        closeProgress: function () {
        	seedProgress.close();
        }
    }
    
    window.LibraryFunc = LibraryFunc;
})(window);