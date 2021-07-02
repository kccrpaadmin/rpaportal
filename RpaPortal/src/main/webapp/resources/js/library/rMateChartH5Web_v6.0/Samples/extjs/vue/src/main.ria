import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import DefaultRoute from './components/DefaultRoute'

Vue.config.productionTip = false

require("./assets/rMateChartH5/Assets/Css/rMateChartH5.css");
require("./js/rMateChartH5/LicenseKey/rMateChartH5License.js");
require("./js/rMateChartH5/JS/rMateChartH5.js");
require("./samples.js"); // 샘플 데이터

// rMateChartH5 차트 컴포넌트
import Chart from "@/components/rMateChartH5.vue";

Vue.use(Router);

var sample,
	routes = [{
		path : "/",
		component : DefaultRoute
	}];

window.rMateChartH5.patternImageBaseUrl = "/assets/rMateChartH5/Assets/Patterns/";
window.rMateChartH5.patternImagesUrl = [
	"diagonal_ltr.png",
	"diagonal_rtl.png",
	"diagonal.png",
	"horizontal.png",
	"vertical.png",
	"both.png",
	"up.png",
	"right.png",
	"horizontal_curve.png",
	"vertical_curve.png",
	"horizontal_pipe.png",
	"vertical_pipe.png",
	"rectangle.png",
	"circle.png",
	"diamond.png",
	"triangle.png",
	"down_triangle.png",
	"cross.png",
	"rectangle2.png",
	"circle.png"
];

// rMateChartH5 의 레이아웃에서 정의한 콜백함수가 등록될 객체
window.rMateChartH5.methods = {};

// samples.js 의 데이터를 가지고 component 를 설정하고 각 샘플에 맞는 ...Sample.js 안의 설정 값을 props에 저장한다.
for(sample in samples){
	sample = samples[sample];
	loadSamples(sample);
	
	sample.component = Chart; // component 를 rMateChartH5.vue 로 설정
	routes.push(sample);
}

/**
 * sample.js 를 가져와 해당 샘플에 정의된 내용을 토대로 router-view에 출력될 내용을 가공한다.
 * 
 * @param {object} sample - samples.js 에 설정된 배열 데이터 안의 하나의 객체 데이터
 */
function loadSamples(sample){
	// 현재 선택된 샘플 js안의 데이터를 가져온다
	let name = sample.name,
		data = require("@/samples/" + sample.js + ".js");
		
	sample.props = {
		layout : data.layoutStr,
		data : data.chartData
	};
	
	// 기본 차트 아이디 이외에 설정 하였을 경우
	if(data.chartId)
		sample.props.chartId = data.chartId;
		
	// 기본 차트 홀더 아이디 이외에 설정 하였을 경우
	if(data.holderId)
		sample.props.holderId = data.holderId;
		
	if(data.chartVars)
		sample.props.chartVars = data.chartVars;
		
	// 샘플 레이아웃에서 직접 함수를 사용하지 않고 methods에 설정하였을경우
	if(data.methods)
		window.rMateChartH5.methods[sample.js] = data.methods;
}

new Vue({
	router : new Router({
		mode: 'history',
		base: process.env.BASE_URL,
		routes
	}),
	render(){
		return (
			<App samples={samples}/>
		);
	}
}).$mount('#app')
