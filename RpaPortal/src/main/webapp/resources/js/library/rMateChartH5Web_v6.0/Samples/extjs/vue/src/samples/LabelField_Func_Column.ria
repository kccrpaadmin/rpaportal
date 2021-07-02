this.layoutStr = {
	rMateChart : {
		backgroundColor : "#ffffff",
		borderStyle : "none",
		Options : {
			Caption : {
				text : "Balance on Current Account",
			},
			SubCaption : {
				text : "( billion $ )",
				textAlign : "right"
			},
			Legend : {
				useVisibleCheck : true,
				defaultMouseOverAction : false
			}
		},
		NumberFormatter : {
			id : "numFmt",
			useThousandsSeparator : true
		},
		Column2DChart : {
			showDataTips : true,
			horizontalAxis : {
				CategoryAxis : {
					categoryField : "Month"
				}
			},
			verticalAxis : {
				LinearAxis : {
					formatter : "{numFmt}",
					maximum : 100,
					interval : 10
				}
			},
			series :[{
				Column2DSet : {
					type : "stacked",
					showTotalLabel : true,
					totalLabelJsFunction : "window.rMateChartH5.methods.LabelField_Func_Column.totalLabelFunc",
					series : [{
						Column2DSeries : {
							color : "#ffffff",
							labelPosition : "inside",
							yField : "goods",
							displayName : "goods",
							insideLabelJsFunction : "rMateChartH5.methods.LabelField_Func_Column.seriesLabelFunc",
							showDataEffect : {
								SeriesInterpolate : {}
							}
						}
					},{
						Column2DSeries : {
							color : "#ffffff",
							labelPosition : "inside",
							yField : "income",
							displayName : "income",
							insideLabelJsFunction : "rMateChartH5.methods.LabelField_Func_Column.seriesLabelFunc",
							showDataEffect : {
								SeriesInterpolate : {}
							}
						}
					},{
						Column2DSeries : {
							color : "#ffffff",
							labelPosition : "inside",
							yField : "service",
							displayName : "service",
							insideLabelJsFunction : "rMateChartH5.methods.LabelField_Func_Column.seriesLabelFunc",
							showDataEffect : {
								SeriesInterpolate : {}
							}
						}
					}]
				}
			}]
		}
	}
};

this.chartData = [{"Month":"Jan","service":12,"goods":11,"income":12},
	{"Month":"Feb","service":14,"goods":19,"income":17},
	{"Month":"Mar","service":23,"goods":25,"income":20},
	{"Month":"Apr","service":20,"goods":20,"income":18},
	{"Month":"May","service":35,"goods":25,"income":25},
	{"Month":"Jun","service":20,"goods":22,"income":23},
	{"Month":"Jul","service":17,"goods":20,"income":17},
	{"Month":"Aug","service":23,"goods":21,"income":21},
	{"Month":"Sep","service":18,"goods":17,"income":10}
];

// 레이아웃에 설정된 콜백함수
this.methods = {
	seriesLabelFunc : function(seriesId, index, data, values){
		return "$" + values[1];
	},
	totalLabelFunc : function(index, data, total){
		return "$" + total;
	}
};
