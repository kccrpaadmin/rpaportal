this.layoutStr = {
	rMateChart : {
		backgroundColor : "#ffffff",
		borderStyle : "none",
		Options : {
			Caption : {
				text : "조건에 따라 색상 결정 사용자 정의 예제"
			},
			SubCaption : {
				text : "데이터 값을 기준으로 채우기 색을 결정합니다.",
				textAlign : "right",
				fontSize : 11
			}
		},
		NumberFormatter : {
			id : "numFmt"
		},
		Column3DChart : {
			showDataTips : true,
			gutterLeft : 0,
			showLabelVertically : true,
			horizontalAxis : {
				CategoryAxis : {
					id : "hAxis",
					categoryField : "Month"
				}
			},
			verticalAxis : {
				LinearAxis : {
					id : "vAxis"
				}
			},
			series : [{
				Column3DSeries : {
					yField : "Profit",
					formatter : "{numFmt}",
					fillJsFunction : function(seriesId, index, data, values){
						if(values[1] > 2000)
							return {"color":"#20cbc2", "alpha":0.7};
						else if(values[1] > 1000)
							return "#5587a2";
						else
							return {"color":"#f6a54c", "alpha":0.5};	
					},
					styleName : "seriesStyle",
					showDataEffect : {
						SeriesSlide : {
							direction : "up",
							duration : 1000
						}
					}
				}
			}],
			verticalAxisRenderers : [{
				Axis3DRenderer : {
					axis : "{vAxis}",
					visible : false
				}
			}]
		},
		Style : {
			".seriesStyle" : {
				labelPosition : "inside",
				fontSize : 11,
				color : "#ffffff"
			}
		}
	}
};

this.chartData = [{"Month":"Jan","Profit":900},
	{"Month":"Feb","Profit":1400},
	{"Month":"Mar","Profit":1500},
	{"Month":"Apr","Profit":1900},
	{"Month":"May","Profit":1400},
	{"Month":"Jun","Profit":2000},
	{"Month":"Jul","Profit":1800},
	{"Month":"Aug","Profit":2500},
	{"Month":"Sep","Profit":3000},
	{"Month":"Oct","Profit":2000},
	{"Month":"Nov","Profit":2100},
	{"Month":"Dec","Profit":1700}
];
