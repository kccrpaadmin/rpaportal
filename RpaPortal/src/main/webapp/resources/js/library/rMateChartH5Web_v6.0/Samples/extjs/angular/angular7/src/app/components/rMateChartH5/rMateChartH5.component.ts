import { Component, OnInit, Input, AfterViewInit, OnChanges, NgModule } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

@Component({
	selector: 'rMateChartH5',
	templateUrl: './rMateChartH5.component.html',
	styleUrls: ['./rMateChartH5.component.css']
})

export class rMateChartH5 implements OnInit{
	
	// 차트 기본 아이디
	@Input()
	chartId:string = "chart";
	
	// 차트 기본 부모 아이디
	@Input()
	holderId:string = "chartHolder";
	
	// 차트 기본 파라메터
	@Input()
	chartVars:string = "";
	
	// 차트 레이아웃
	@Input()
	layout:any;
	
	// 차트 데이터
	@Input()
	data:any;
	
	// rMateChartH5.js 객체
	chart:any;

	constructor(private route:ActivatedRoute){

		// rMateChart 객체 설정
		this.chart = window["rMateChartH5"];

		// 패턴 이미지 기본 경로
		this.chart.patternImageBaseUrl = "/assets/rMateChartH5/Assets/Patterns/";

		// 각 패턴 이미지들 경로
		this.chart.patternImagesUrl = [
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
	
	ngOnInit(){
		let p:any,
			route = this.route;

		// 라우터를 통해 데이터를 넘겼을 경우 data로 넘어온 값들을
		// 현재 컴포넌트 값으로 설정
		if(route && route.data){
			route.data.subscribe(data => {
				p = data;
			});

			if(p.chartId)
				this.chartId = p.chartId;
			if(p.holderId)
				this.holderId = p.holderId;
			if(p.layout)
				this.layout = p.layout;
			if(p.chartVars)
				this.chartVars = p.chartVars;
			if(p.data)
				this.data = p.data;
		}
	}
	
	ngAfterViewInit(){

		// 차트 생성
		this.create();

		// 레이아웃
		if(this.layout)
			this.setLayout(this.layout);
		
		// 데이터
		if(this.data)
			this.setData(this.data);
	}
	
	/**
	 * 차트 wrapper 생성
	 */
	create(){
		this.chart.create(this.chartId, this.holderId, this.chartVars);
	}
	
	/**
	 * 차트의 레이아웃을 설정합니다.
	 * 
	 * @param layout - 차트 레이아웃
	 */
	setLayout(layout){
		this.call("setLayout", layout);
	}
	
	/**
	 * 차트의 데이터를 설정합니다.
	 * 
	 * @param data - 차트 데이터
	 */
	setData(data){
		this.call("setData", data);
	}

	call(...params){
		this.chart.call(this.chartId, ...params);
	}
}
