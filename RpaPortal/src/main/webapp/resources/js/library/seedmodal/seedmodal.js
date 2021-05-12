(function (window) {
    // 공개영역
    var SeedModal = function () {
        this.config = {
            smTheme: undefined,
            smTop: undefined,
            smLeft: undefined,
            smWidth: undefined,
            smHeight: undefined,
            smTitle: undefined,
            smUrl: undefined
        };

        this.setConfig = function (cfg) {
            this.config.smTheme = cfg.smTheme;
            this.config.smTop = cfg.smTop;
            this.config.smLeft = cfg.smLeft;
            this.config.smWidth = cfg.smWidth;
            this.config.smHeight = cfg.smHeight;
            this.config.smTitle = cfg.smTitle;
            this.config.smUrl = cfg.smUrl;
        };

        // 모달팝업이 열려있는 상태에서, 새로운 모달팝업도 열수 있기 때문에
        // 객체에 전역변수에 모달팝업을 넣는 행위가 의미가 없어서 정의하지 않음
    }

    // 비공개 영역 전역변수
    var _agent = navigator.userAgent;       // 브라우져 정보 전역변수
    var _smZIndex = undefined;              // 마스크 zindex 전역변수
    var _smMaskElmt = undefined;            // 마스크 박스 전역변수
    var _smModalBoxElmt = undefined;        // 모달팝업 전역변수
    var _smTitleBoxElmt = undefined;        // 제목박스 전역변수
    var _smBodyBoxElmt = undefined;         // 본문박스 전역변수

    // 모달팝업 생성 함수
    SeedModal.prototype.modal = function (pModal, pUserCfg) {
        // 스크롤 없애는 로직
        smCommonFunc.setScrollHidden();

        // 현재 생성된 대화상자, 모달팝업 마스크의 인덱스(+1)를 구함
        _smZIndex = smCommonFunc.getZindex();

        // 레이어 마스크
        var smMask = document.createElement("div");
        _smMaskElmt = smMask;
        _smMaskElmt.className = "sm_mask";
        _smMaskElmt.style.zIndex = _smZIndex;
        _smMaskElmt.style.width = "100%"
        _smMaskElmt.style.height = "100%"
        document.body.appendChild(_smMaskElmt);
        
        // 모달팝업 박스
        var smModalBox = document.createElement("div");
        _smModalBoxElmt = smModalBox;

        // 제목 박스
        var smTitleBox = document.createElement("div");
        _smTitleBoxElmt = smTitleBox;
        _smTitleBoxElmt.className = "sm_title_box";
        smCommonFunc.setTitleBox(pModal, pUserCfg);
        _smModalBoxElmt.appendChild(_smTitleBoxElmt);

        // 본문 박스
        var smBodyBox = document.createElement("div");
        _smBodyBoxElmt = smBodyBox;
        _smBodyBoxElmt.className = "sm_body_box";
        smCommonFunc.setBodyBox(pModal, pUserCfg);
        _smModalBoxElmt.appendChild(_smBodyBoxElmt);
        document.body.appendChild(_smModalBoxElmt);
        smCommonFunc.setModalBox(pModal, pUserCfg);
    }

    SeedModal.prototype.close = function () {
        smCommonFunc.setClose();
    }

    // 비공개 영역 함수

    // 모달팝업 공통 연관 함수
    var smCommonFunc = {
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
        getTopPos: function (pModal, pUserCfg) {
            var topPos = undefined;
            var topValue = pUserCfg.smTop == undefined ? pModal.config.smTop : pUserCfg.smTop;

            if (topValue == "center") {
                topPos = (window.innerHeight / 2) - (_smModalBoxElmt.clientHeight / 2);
            }
            else {
                topPos = pUserCfg.smTop == undefined ? pModal.config.smTop : pUserCfg.smTop;
            }

            topPos = topPos + "px";

            return topPos;
        },
        getLeftPos: function (pModal, pUserCfg) {
            var leftPos = undefined;
            var leftValue = pUserCfg.smLeft == undefined ? pModal.config.smLeft : pUserCfg.smLeft;

            if (leftValue == "middle") {
                leftPos = (window.innerWidth / 2) - (_smModalBoxElmt.clientWidth / 2);
            }
            else {
                leftPos = pUserCfg.smLeft == undefined ? pModal.config.smLeft : pUserCfg.smLeft;
            }

            leftPos = leftPos + "px";

            return leftPos;
        },
        setScrollHidden: function () {
            // 스크롤 없애기
            document.body.style.overflowY = "hidden";
        },
        setTitleBox: function (pModal, pUserCfg) {
            // 제목 박스에 제목 삽입 (사용자가 넘긴 설정값이 우선)
            _smTitleBoxElmt.innerHTML = pUserCfg.smTitle == undefined ? pModal.config.smTitle : pUserCfg.smTitle;

            // 제목 박스 마우스 다운 이벤트 (모달팝업 드래그 이동 시작)
            // 모달팝업의 iframe내 자식 document가 있어서 이벤트가 중복 됨
            // 시도 가능 방법을 Views-Iframe-SaveData.cshtml에 정의해 놓음
            // 별도의 처리없이 공통으로 처리함
            _smTitleBoxElmt.addEventListener("mousedown", function (e) {
                e.preventDefault();
                smMoveFunc.startDrag(e, _smModalBoxElmt);
            }, false);

            // 닫기 버튼 생성
            var btnClose = document.createElement("a");
            btnClose.addEventListener("mousedown", function (e) {
                e.preventDefault();
                smCommonFunc.setClose();
            }, false);
            _smTitleBoxElmt.appendChild(btnClose);
        },
        setBodyBox: function (pModal, pUserCfg) {
            // Iframe 태그 생성
            var smIframe = document.createElement("iframe");
            smIframe.src = pUserCfg.smUrl;
            smIframe.style.width = pUserCfg.smWidth == undefined ? pModal.config.smWidth + "px" : pUserCfg.smWidth + "px";
            smIframe.style.height = pUserCfg.smHeight == undefined ? pModal.config.smHeight + "px" : pUserCfg.smHeight + "px";
            smIframe.scrolling = "yes";
            smIframe.frameBorder = 0;
            smIframe.frameSpacing = 0;
            smIframe.marginWidth = 0;
            smIframe.marginHeight = 0;
            _smBodyBoxElmt.appendChild(smIframe);
        },
        setModalBox: function (pModal, pUserCfg) {
            // 모달팝업 박스 class 설정 (사용자가 넘긴 설정값이 우선)
            _smModalBoxElmt.className = pUserCfg.smTheme == undefined ? pModal.config.smTheme : pUserCfg.smTheme;

            // zIndex 설정 (마스크 보다 1 크게)
            _smModalBoxElmt.style.zIndex = _smZIndex + 1;

            // 모달팝업 박스 너비 설정 (사용자가 넘긴 설정값이 우선)
            _smModalBoxElmt.style.width = pUserCfg.smWidth == undefined ? pModal.config.smWidth + "px" : pUserCfg.smWidth + "px";

            // 모달팝업 박스 top 설정 (사용자가 넘긴 설정값이 우선)
            _smModalBoxElmt.style.top = smCommonFunc.getTopPos(pModal, pUserCfg);

            // 모달팝업 박스 left 설정 (사용자가 넘긴 설정값이 우선)
            _smModalBoxElmt.style.left = smCommonFunc.getLeftPos(pModal, pUserCfg);
        },
        setClose: function () {
            var sdMasks = document.querySelectorAll(".sd_mask");
            var sdMasksLen = sdMasks.length;
            var smMasks = document.querySelectorAll(".sm_mask");
            var smMasksLen = smMasks.length;
            var masksLen = sdMasksLen + smMasksLen;
            var zIndex = 0;
            var targetMask = undefined;

            // 대화상자 및 모달팝업이 하나만 열려있는 경우, 닫기전 스크롤 넣어주기 
            if (masksLen == 1 && (document.documentElement.scrollHeight - document.documentElement.clientHeight > 0)) {
                document.body.style.overflowY = "scroll";
            }

            // 맨앞에 있는 모달팝업 찾기
            if (smMasksLen > 0) {
                for (var i = 0; i < smMasksLen; i++) {
                    if (Number(smMasks[i].style.zIndex) > zIndex) {
                    	zIndex = Number(smMasks[i].style.zIndex);
                        targetMask = smMasks[i];
                    }
                }
            }

            smCommonFunc.removeNode(targetMask.nextSibling);
            smCommonFunc.removeNode(targetMask);
        },
        removeNode: function (pElmt) {
            if (smCommonFunc.getBrowserType() == "IE") {
                pElmt.parentNode.removeChild(pElmt);
            }
            else {
                pElmt.remove();
            }
        }
    }
    
    // 모달 팝업 이동 동작 오류 발생 
    // 소스 위치 (/Views/Iframe/SaveData.cshtml)
    // 자바스크립트 함수로 수정 완료 (cutEventParentDocument)

    var _boxTop = 0;
    var _boxLeft = 0;
    var _targetBox;

    // 모달팝업 이동 연관 함수
    var smMoveFunc = {
        startDrag: function (e, pBox) {
            _targetBox = pBox;
            var evt = window.event ? window.event : e;
            if (evt.preventDefault) {
                evt.stopImmediatePropagation(); // 상위 레벨 및 현재 레벨에 걸린 이벤트 전파 동작 중지
                evt.stopPropagation();          // 상위 레벨에 걸린 이벤트 전파 동작 중지
                evt.preventDefault();           // 현재 이벤트의 기본 동작 및 하위 레벨에 걸린 이벤트 전파 동작 중지
            };

            _boxTop = evt.clientY;
            _boxLeft = evt.clientX;
            document.onmouseup = smMoveFunc.stopDrag;
            document.onmousemove = smMoveFunc.moveDrag;
            return false;
        },
        moveDrag: function (e) {
            var evt = window.event ? window.event : e;
            if (evt.preventDefault) {
                evt.stopImmediatePropagation();
                evt.stopPropagation();
                evt.preventDefault();
            };

            var posTop = _boxTop - evt.clientY;
            var posLeft = _boxLeft - evt.clientX;
            _boxTop = evt.clientY;
            _boxLeft = evt.clientX;
            _targetBox.style.top = (_targetBox.offsetTop - posTop) + "px";
            _targetBox.style.left = (_targetBox.offsetLeft - posLeft) + "px";
            return false;
        },
        stopDrag: function (e) {
            var evt = window.event ? window.event : e;
            if (evt.preventDefault) {
                evt.stopImmediatePropagation();
                evt.stopPropagation();
                evt.preventDefault();
            };
            document.onmousemove = null;
            document.onmouseup = null;
            return false;
        }
    }

    window.SeedModal = SeedModal;
})(window);