(function (window) {
    // 공개영역
    var SeedProgress = function () {}

    // 비공개 영역 전역변수
    var _agent = navigator.userAgent;       // 브라우져 정보 전역변수
    var _spZIndex = undefined;              // 마스크 zindex 전역변수
    var _spMaskElmt = undefined;            // 마스크 박스 전역변수

    // 모달팝업 생성 함수
    SeedProgress.prototype.progress = function (pProgress) {
        // 현재 생성된 대화상자, 모달팝업 마스크의 인덱스(+1)를 구함
        _spZIndex = spCommonFunc.getZindex();

        // 레이어 마스크
        var spMask = document.createElement("div");
        _spMaskElmt = spMask;
        _spMaskElmt.className = "sp_mask";
        _spMaskElmt.style.zIndex = _spZIndex;
        _spMaskElmt.style.width = "100%"
        _spMaskElmt.style.height = "100%"
        document.body.appendChild(_spMaskElmt);
    }

    SeedProgress.prototype.close = function () {
        spCommonFunc.setClose();
    }

    // 비공개 영역 함수

    // 모달팝업 공통 연관 함수
    var spCommonFunc = {
        getBrowserType: function () {
            var agent = navigator.userAgent.toLowerCase();
            var appName = navigator.appName.toLowerCase();
            var browserType = "Standard";

            if ((appName == "netscape" && agent.search("trident") != -1) || (agent.indexOf("msie") != -1)) {
                browserType = "IE";
            }

            return browserType;
        },
        getZindex: function () {
            var sdMasks = document.querySelectorAll(".sd_mask");
            var smMasks = document.querySelectorAll(".sm_mask");
            var zIndex = 0;

            if (sdMasks != undefined) {
                for (var i = 0; i < sdMasks.length; i++) {
                    if (Number(sdMasks[i].style.zIndex) > zIndex) {
                        zIndex = Number(sdMasks[i].style.zIndex);
                    }
                }
            }

            if (smMasks != undefined) {
                for (var i = 0; i < smMasks.length; i++) {
                    if (Number(smMasks[i].style.zIndex) > zIndex) {
                        zIndex = Number(smMasks[i].style.zIndex);
                    }
                }
            }

            if (zIndex == 0) {
                zIndex = 1001;
            }
            else {
                zIndex = zIndex + 1;
            }

            return zIndex;
        },
        setClose: function () {
            var spMask = document.querySelectorAll(".sp_mask");
            var spMaskLen = spMask.length;
            var zIndex = 0;
            var targetMask = undefined;
            
            // 맨앞에 있는 모달팝업 찾기
            if (spMaskLen > 0) {
                for (var i = 0; i < spMaskLen; i++) {
                    if (Number(spMask[i].style.zIndex) > zIndex) {
                    	zIndex = Number(spMask[i].style.zIndex);
                        targetMask = spMask[i];
                    }
                }
            }

            spCommonFunc.removeNode(targetMask);
        },
        removeNode: function (pElmt) {
        	// 혹시 프로그레스 마스크가 생성안된 경우도 에러가 발생하지 않도록 처리
        	if (pElmt != undefined) {
	            if (spCommonFunc.getBrowserType() == "IE") {
	                pElmt.parentNode.removeChild(pElmt);
	            }
	            else {
	                pElmt.remove();
	            }
        	}
        }
    }
    
    window.SeedProgress = SeedProgress;
})(window);