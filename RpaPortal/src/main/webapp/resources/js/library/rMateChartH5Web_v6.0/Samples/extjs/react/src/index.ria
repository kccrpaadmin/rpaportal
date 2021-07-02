/* eslint-disable */

import React from 'react';
import ReactDOM from 'react-dom';

import './index.css';

import App from './App';
import * as serviceWorker from './serviceWorker';
import samples from "./samples";

for(let sample in samples){
	sample = samples[sample];
	loadSamples(sample);
}

/**
 * sample.js 를 가져와 해당 샘플에 정의된 내용을 토대로 router-view에 출력될 내용을 가공한다.
 * 
 * @param {object} sample - samples.js 에 설정된 배열 데이터 안의 하나의 객체 데이터
 */
function loadSamples(sample){
	// 현재 선택된 샘플 js안의 데이터를 가져온다
	const name = sample.name,
		data = require("./samples/" + sample.js + ".js");
		
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
}

ReactDOM.render(<App samples={samples}/>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
