(function (window) {
    // 공개영역
    var SeedDialog = function () {
        this.config = {
            sdTheme: undefined,
            sdTop: undefined,
            sdLeft: undefined,
            sdWidth: undefined,
            sdTitle: undefined
        };

        this.setConfig = function (cfg) {
            this.config.sdTheme = cfg.sdTheme;
            this.config.sdTop = cfg.sdTop;
            this.config.sdLeft = cfg.sdLeft;
            this.config.sdWidth = cfg.sdWidth;
            this.config.sdTitle = cfg.sdTitle;
        };

        // sdTitleBoxElmt, sdBodyBoxElmt, sdBtnBoxElmt 해당 엘리먼트는 객체 전역변수 보다는 js 파일내 전역변수가 어울린다.
        // 객체의 option에 sdMaskElmt, sdDialogBoxElmt 해당 엘리먼트는 대화상자를 닫을때 사용하나, 
        // 모달팝업과 같이 가장 앞에 있는 창을 닫으면 객체에 가질 필요성은 없어진다.
        // 객체의 option엔 리턴값만 가진다.
        this.option = {
            sdBtnKey: undefined,            // 사용자 버튼 클릭 Key 전역변수
            sdPromptValues: {}              // 사용자 입력 데이터 프롬프트 전역변수
        };
    }

    // 비공개 영역 전역변수
    var _agent = navigator.userAgent;       // 브라우져 정보 전역변수
    var _sdZIndex = undefined;              // 마스크 zindex 전역변수
    var _sdMaskElmt = undefined;            // 마스크 박스 전역변수
    var _sdDialogBoxElmt = undefined;       // 대화상자 전역변수 (닫을때 사용)
    var _sdTitleBoxElmt = undefined;        // 제목박스 전역변수
    var _sdBodyBoxElmt = undefined;         // 본문박스 전역변수
    var _sdBtnBoxElmt = undefined;          // 버튼박스 전역변수

    // 대화상자 생성 함수
    SeedDialog.prototype.dialog = function (pDialog, pType, pUserCfg, callback) {
        // 스크롤 없애는 로직
        sdCommonFunc.setScrollHidden();

        // 현재 생성된 대화상자, 모달팝업 마스크의 인덱스(+1)를 구함
        _sdZIndex = sdCommonFunc.getZindex();

        // 레이어 마스크 생성 및 삽입
        var sdMask = document.createElement("div");
        _sdMaskElmt = sdMask; // 마스크 전역 변수 삽입
        _sdMaskElmt.className = "sd_mask";
        _sdMaskElmt.style.zIndex = _sdZIndex;
        _sdMaskElmt.style.width = "100%"
        _sdMaskElmt.style.height = "100%"
        document.body.appendChild(_sdMaskElmt);
        
        // 대화상자 박스 생성
        var sdDialogBox = document.createElement("div");
        _sdDialogBoxElmt = sdDialogBox; // 대화상자 전역 변수 삽입

        // 제목박스 생성 및 삽입
        var sdTitleBox = document.createElement("div");
        _sdTitleBoxElmt = sdTitleBox; // 제목박스 전역 변수 삽입
        _sdTitleBoxElmt.className = "sd_title_box";
        sdCommonFunc.setTitleBox(pDialog, pUserCfg);
        _sdDialogBoxElmt.appendChild(_sdTitleBoxElmt);

        // 본문박스 생성 및 삽입
        var sdBodyBox = document.createElement("div");
        _sdBodyBoxElmt = sdBodyBox; // 본문박스 전역 변수 삽입
        _sdBodyBoxElmt.className = "sd_body_box";
        sdCommonFunc.setBodyBox(pDialog, pType, pUserCfg);
        _sdDialogBoxElmt.appendChild(_sdBodyBoxElmt);

        // 버튼박스 생성 및 삽입
        var sdBtnBox = document.createElement("div");
        _sdBtnBoxElmt = sdBtnBox; // 버튼박스 전역 변수 삽입
        _sdBtnBoxElmt.className = "sd_btn_box";
        sdCommonFunc.setBtnBox(pDialog, pType, pUserCfg, callback);
        _sdDialogBoxElmt.appendChild(_sdBtnBoxElmt);

        // 대화상자 박스 삽입
        document.body.appendChild(_sdDialogBoxElmt);

        // 대화상자 박스 설정 (타입, 위치)
        sdCommonFunc.setDialogBox(pDialog, pUserCfg);

        // 첫번째 버튼에 포커스
        _sdDialogBoxElmt.children[2].children[0].focus();
    }

    // 대화상자 닫기 함수
    SeedDialog.prototype.close = function () {
        sdCommonFunc.setClose();
    }

    // 비공개 영역 함수

    // 대화상자 공통 연관 함수
    var sdCommonFunc = {
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
        getTopPos: function (pDialog, pUserCfg) {
            var topPos = undefined;
            var topValue = pUserCfg.sdTop == undefined ? pDialog.config.sdTop : pUserCfg.sdTop;

            if (topValue == "center") {
                topPos = (window.innerHeight / 2) - (_sdDialogBoxElmt.clientHeight / 2);
            }
            else {
                topPos = pUserCfg.sdTop == undefined ? pDialog.config.sdTop : pUserCfg.sdTop;
            }

            topPos = topPos + "px";

            return topPos;
        },
        getLeftPos: function (pDialog, pUserCfg) {
            var leftPos = undefined;
            var leftValue = pUserCfg.sdLeft == undefined ? pDialog.config.sdLeft : pUserCfg.sdLeft;

            if (leftValue == "middle") {
                leftPos = (window.innerWidth / 2) - (_sdDialogBoxElmt.clientWidth / 2);
            }
            else {
                leftPos = pUserCfg.sdLeft == undefined ? pDialog.config.sdLeft : pUserCfg.sdLeft;
            }

            leftPos = leftPos + "px";

            return leftPos;
        },
        setScrollHidden: function () {
            // 스크롤 없애기
            document.body.style.overflowY = "hidden";
        },
        setTitleBox: function (pDialog, pUserCfg) {
            // 제목박스에 제목 삽입 (사용자가 넘긴 설정값이 우선)
            _sdTitleBoxElmt.innerHTML = pUserCfg.sdTitle == undefined ? pDialog.config.sdTitle : pUserCfg.sdTitle;

            // 제목박스 마우스 다운 이벤트 (대화상자 드래그 이동 시작)
            _sdTitleBoxElmt.addEventListener("mousedown", function (e) {
                e.preventDefault();
                sdMoveFunc.startDrag(e, _sdDialogBoxElmt);
            }, false);

            // 닫기버튼 생성
            var btnClose = document.createElement("a");
            btnClose.addEventListener("mousedown", function (e) {
                e.preventDefault();
                sdCommonFunc.setClose();
            }, false);
            _sdTitleBoxElmt.appendChild(btnClose);
        },
        setBodyBox: function (pDialog, pType, pUserCfg) {
            // 프롬프트 타입인 경우
            if (pType == "Prompt") {
                var promptList = pUserCfg.sdPromptList;
                var promptListLen = promptList.length;

                // 프롬프트 목록 루프
                for (var i = 0; i < promptListLen; i++) {
                    // label 태그 생성
                    var label = document.createElement("label");
                    label.innerHTML = promptList[i].label;
                    _sdBodyBoxElmt.appendChild(label);

                    // br 태그 생성
                    var br = document.createElement("br");
                    _sdBodyBoxElmt.appendChild(br);

                    // input 태그 생성
                    var input = document.createElement("input");
                    input.type = "text";

                    // 박스 너비보다 50px 작은 사이즈로 설정
                    input.style.width = pUserCfg.sdWidth == undefined ? Number(pDialog.config.sdWidth) - 50 + "px" : Number(pUserCfg.sdWidth) - 50 + "px";

                    // 프롬프트 Key 생성
                    input.setAttribute("promptKey", promptList[i].promptKey);
                    _sdBodyBoxElmt.appendChild(input);

                    // 마지막 input box 경우
                    // 버튼 영역의 마진과 중복으로 br 태그 하나만 생성
                    if (i == promptListLen - 1) {
                        var br = document.createElement("br");
                        _sdBodyBoxElmt.appendChild(br);
                    }
                    else {
                        var br = document.createElement("br");
                        _sdBodyBoxElmt.appendChild(br);

                        var br = document.createElement("br");
                        _sdBodyBoxElmt.appendChild(br);
                    }
                }
            }
            else {
                // 프롬프트 타입이 아닌 경우 (메세지만 넣음)
                _sdBodyBoxElmt.innerHTML = pUserCfg.sdMsg;
            }
        },
        setBtnBox: function (pDialog, pType, pUserCfg, callback) {
            var btnList = pUserCfg.sdBtnList;
            var btnListLen = btnList.length;

            // 버튼 목록 루프
            for (var i = 0; i < btnListLen; i++) {
                // a 태그 생성
                var sdBtn = document.createElement("a");
                sdBtn.innerText = btnList[i].btnNm;

                // 버튼 Key 생성
                sdBtn.setAttribute("btnKey", btnList[i].btnKey);

                // 탭키로 index 이동 되도록 설정
                sdBtn.href = "#";

                // 버튼 마우스 클릭 이벤트
                sdBtn.addEventListener("click", function (e) {
                    e.preventDefault();
                    sdCommonFunc.setClose();
                    sdCommonFunc.setOption(this, pDialog, pType, callback);
                }, false);

                // 버튼 키보드 엔터, 스페이스키 이벤트
                sdBtn.addEventListener("keydown", function (e) {
                    if (e.keyCode == 13) {
                        e.preventDefault();
                        sdCommonFunc.setClose();
                        sdCommonFunc.setOption(this, pDialog, pType, callback);
                    }
                    else if (e.keyCode == 32) {
                        e.preventDefault();
                        sdCommonFunc.setClose();
                        sdCommonFunc.setOption(this, pDialog, pType, callback);
                    }
                }, false);

                _sdBtnBoxElmt.appendChild(sdBtn);
            }
        },
        setOption: function (pBtn, pDialog, pType, callback) {
            // 개발자가 확인 가능한 값 설정
            pDialog.option.sdBtnKey = pBtn.getAttribute("btnkey");

            // 프롬프트 타입인 경우
            if (pType == "Prompt") {
                var bodyBox = _sdDialogBoxElmt.querySelector(".sd_body_box");

                // sd_body_box 안의 엘리먼트 중 INPUT만 찾아서 Key,Value를 sdPromptValues에 저장한다.
                for (var i = 0; i < bodyBox.childNodes.length; i++) {
                    if (bodyBox.childNodes[i].nodeName == "INPUT") {
                        pDialog.option.sdPromptValues[bodyBox.childNodes[i].getAttribute("promptKey")] = bodyBox.childNodes[i].value;
                    }
                }
            }

            // callback.apply(this, 배열);
            // 현재 this는 sdBtn 이다 this 위치에 pDialog 넘겨서 this를 재정의해서 사용 가능 (callback.apply(pDialog);)
            // option을 넣은 값을 콜백함수에서 사용하기 위해서 pDialog.option을 넘김
            if (typeof callback === "function") {
                callback.apply(pBtn, [pDialog.option]);
            }
        },
        setDialogBox: function (pDialog, pUserCfg) {
            // 대화상자 박스 class 설정 (사용자가 넘긴 설정값이 우선)
            _sdDialogBoxElmt.className = pUserCfg.sdTheme == undefined ? pDialog.config.sdTheme : pUserCfg.sdTheme;

            // zIndex 설정 (마스크 보다 1 크게)
            _sdDialogBoxElmt.style.zIndex = _sdZIndex + 1;

            // 대화상자 박스 너비 설정 (사용자가 넘긴 설정값이 우선)
            _sdDialogBoxElmt.style.width = pUserCfg.sdWidth == undefined ? pDialog.config.sdWidth + "px" : pUserCfg.sdWidth + "px";

            // 대화상자 박스 top 설정 (사용자가 넘긴 설정값이 우선)
            _sdDialogBoxElmt.style.top = sdCommonFunc.getTopPos(pDialog, pUserCfg);

            // 대화상자 박스 left 설정 (사용자가 넘긴 설정값이 우선)
            _sdDialogBoxElmt.style.left = sdCommonFunc.getLeftPos(pDialog, pUserCfg);
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

            // 맨앞에 있는 대화상자 찾기
            if (sdMasksLen > 0) {
                for (var i = 0; i < sdMasksLen; i++) {
                    if (Number(sdMasks[i].style.zIndex) > zIndex) {
                    	zIndex = Number(sdMasks[i].style.zIndex);
                        targetMask = sdMasks[i];
                    }
                }
            }

            sdCommonFunc.removeNode(targetMask.nextSibling);
            sdCommonFunc.removeNode(targetMask);
        },
        removeNode: function (pElmt) {
            if (sdCommonFunc.getBrowserType() == "IE") {
                pElmt.parentNode.removeChild(pElmt);
            }
            else {
                pElmt.remove();
            }
        }
    }

    var _boxTop = 0;
    var _boxLeft = 0;
    var _targetBox;

    // 대화상자 이동 연관 함수
    var sdMoveFunc = {
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
            document.onmouseup = sdMoveFunc.stopDrag;
            document.onmousemove = sdMoveFunc.moveDrag;
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

    window.SeedDialog = SeedDialog;
})(window);