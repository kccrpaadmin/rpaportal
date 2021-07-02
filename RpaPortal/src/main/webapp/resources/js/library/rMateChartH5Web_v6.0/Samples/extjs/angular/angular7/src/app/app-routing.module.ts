import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { samples } from "./samples";
import { Welcome } from "./components/Welcome/Welcome.component";
import { ColumnChart } from "./components/ColumnChart/ColumnChart.component";
import { rMateChartH5 } from "./components/rMateChartH5/rMateChartH5.component";

// 샘플을 위한 라우터 설정
let routes:Object[] = [{
		path : "",
		component : Welcome
	},{
		path : "default", // ./samples.js 안에 있는 샘플 말고 컴포넌트 안에서 차트를 생성하는 샘플
		component : ColumnChart
	}];

// ./samples.js 에 설정해둔 샘플 레이아웃, 데이터들을 각 샘플 route에 넣어주도록 합니다.
samples.forEach(function(sample){
	loadSamples(sample);

	(<any>sample).component = rMateChartH5;
	routes.push(sample);
});

// ./samples폴더의 샘플 js파일들에서 레이아웃, 데이터 들을 가져와 route에 값을 저장해둡니다.
//
// 이는 여러 샘플을 보여주기 위하여 router 를 통하여 데이터를 넘겨 차트 출력을 위하여 진행한 방법으로
// 꼭 아래와 진행하지 않아도 되며 /basic 샘플처럼 컴포넌트 안에 레이아웃과 데이터를 넣어 차트를 출력하여도 됩니다.
function loadSamples(sample){
	const data = require("./samples/" + sample.js + ".js");

	sample.data = {
		layout : data.layoutStr,
		data : data.chartData
	};

	// 기본 차트 아이디 이외에 설정 하였을 경우
	if(data.chartId)
		sample.data.chartId = data.chartId;
		
	// 기본 차트 홀더 아이디 이외에 설정 하였을 경우
	if(data.holderId)
		sample.data.holderId = data.holderId;
	
	// 기본 차트 vars 이외에 설정 하였을 경우
	if(data.chartVars)
		sample.data.chartVars = data.chartVars;
}

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers : [
	  {provide : "samples", useValue : samples}
  ]
})
export class AppRoutingModule { }
