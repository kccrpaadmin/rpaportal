import React, {Component} from "react";
import PropTypes from "prop-types";

import "../assets/rMateChartH5/Assets/Css/rMateChartH5.css";
import "../js/rMateChartH5/JS/rMateChartH5.js";
import "../js/rMateChartH5/LicenseKey/rMateChartH5License.js";

export default class rMateChartH5 extends Component{
	constructor(props){
		super(props);

		// rMateChart 객체 설정
		this.chart = window.rMateChartH5;

		window.rMateChartH5.patternImageBaseUrl = "/assets/rMateChartH5/Assets/Patterns/";

		// 각 패턴 이미지들 경로
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
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//	Life-Cycle
	//
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	render(){
		return (
			<div id={this.props.holderId} className="chartHolder"></div>
		);
	}
	
	componentDidMount(){
		let props = this.props;
		this.create();

		if(props.layout)
			this.setLayout(props.layout);
		
		if(props.data)
			this.setData(props.data);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//	methods
	//
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * rMateChartH5 객체 생성
	 */
	create(){
		const props = this.props;
		this.chart.create(props.chartId, props.holderId, props.chartVars);
	}
	
	call(...parmas){
		this.chart.call(this.props.chartId, ...parmas);
	}
	
	/**
	 * 레이아웃 설정
	 */
	setLayout(layout){
		this.call("setLayout", layout);
	}
	
	/**
	 * 데이터 설정
	 */
	setData(data){
		this.call("setData", data);
	}
	
	/**
	 * 프리로더 출력
	 */
	showAdditionalPreloader(){
		this.call("showAdditionalPreloader");
	}
	
	/**
	 * 프리로더 삭제
	 */
	removeAdditionalPreloader(){
		this.call("removeAdditionalPreloader");
	}
}

// 기본 파라메터 값 타입
rMateChartH5.propTypes = {
	chartId : PropTypes.string, // 차트 아이디
	holderId : PropTypes.string, // 차트가 그려질 객체 아이디
	chartVars : PropTypes.string,
	layout : PropTypes.oneOfType([ // 차트 레이아웃
		PropTypes.object, 
		PropTypes.string
	]),
	data : PropTypes.oneOfType([ // 차트 데이터
		PropTypes.object,
		PropTypes.array
	])
};

// 기본 값
rMateChartH5.defaultProps = {
	chartId : "chart",
	holderId : "chartHolder",
	chartVars : ""
};
