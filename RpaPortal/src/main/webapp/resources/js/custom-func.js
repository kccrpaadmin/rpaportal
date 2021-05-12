(function (window) {
    // 함수 호출 못하도록 처리 가능

    // Date 정의시 포멧 정의 함수 (new Date)
    Date.prototype.format = function (f) {
        if (!this.valueOf()) {
            return ""
        };

        var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
        var d = this;

        return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function ($1) {
            switch ($1) {
                case "yyyy": return d.getFullYear();
                case "yy": return (d.getFullYear() % 1000).zf(2);
                case "MM": return (d.getMonth() + 1).zf(2);
                case "dd": return d.getDate().zf(2);
                case "E": return weekName[d.getDay()];
                case "HH": return d.getHours().zf(2);
                case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
                case "mm": return d.getMinutes().zf(2);
                case "ss": return d.getSeconds().zf(2);
                case "a/p": return d.getHours() < 12 ? "오전" : "오후";
                default: return $1;
            }
        });
    };

    // Date.prototype.format 문자형 정의 함수
    String.prototype.string = function (len) {
        var s = "", i = 0;
        while (i++ < len) {
            s += this;
        }
        return s;
    };

    // Date.prototype.format 문자형 정의 함수
    String.prototype.zf = function (len) {
        return "0".string(len - this.length) + this;
    };

    // Date.prototype.format 숫자형 정의 함수
    Number.prototype.zf = function (len) {
        return this.toString().zf(len);
    };

    // Jquery 값 변경 이벤트 트리거 함수
    // onchange는 text box에서 포커스가 벗어날때 발생
    // Jquery로 값 입력은 되지만 포커스가 벗어나지 않기 때문에 별도로 트리거를 걸어줘야 한다. ex : $("#id").trigger("change");
    // 별도의 트리거를 걸어 주지 않고 함수 정의를 통해서 처리 가능 ex : $("#id").valChange("입력값");
    $.fn.valChange = function () {
        var prev;
        if (arguments.length > 0) {
            prev = $.fn.val.apply(this, []);
        }
        var result = $.fn.val.apply(this, arguments);
        if (arguments.length > 0 && prev != $.fn.val.apply(this, [])) {
            $(this).trigger("change");
        }
        return result;
    };

})(window);