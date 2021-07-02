this.chartVars = "usePattern=true";

this.layoutStr = {
	rMateChart : {
		backgroundColor : "#ffffff",
		borderStyle : "none",
		Options : {
			Caption : {
				text : "Pattern of Column 2D Chart"
			},
			Legend : {
				defaultMouseOverAction : true
			}
		},
		NumberFormatter  : {
			id : "numFmt",
			useThousandsSeparator : true
		},
		Column2DChart : {
			showDataTips : true,
			patternMode : true,
			horizontalAxis : {
				CategoryAxis : {
					categoryField : "Month"
				}
			},
			verticalAxis : {
				LinearAxis : {
					formatter : "{numFmt}"
				}
			},
			series : [{
				Column2DSet : {
					type : "stacked",
					showTotalLabel : true,
					series : [{
						Column2DSeries : {
							yField : "Profit",
							displayName : "Profit",
							showDataEffect : {
								SeriesInterpolate : {}
							}
						}
					},{
						Column2DSeries : {
							yField : "Cost",
							displayName : "Cost",
							showDataEffect : {
								SeriesInterpolate : {}
							}
						}
					},{
						Column2DSeries : {
							yField : "Revenue",
							displayName : "Revenue",
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

this.chartData = [{"Month":"Jan","Profit":1000,"Cost":1500,"Revenue":2300},
	{"Month":"Feb","Profit":1400,"Cost":1400,"Revenue":1200},
	{"Month":"Mar","Profit":1500,"Cost":1200,"Revenue":1600},
	{"Month":"Apr","Profit":1900,"Cost":1300,"Revenue":1300},
	{"Month":"May","Profit":1400,"Cost":900,"Revenue":1000},
	{"Month":"Jun","Profit":2000,"Cost":500,"Revenue":1200},
	{"Month":"Jul","Profit":1800,"Cost":1400,"Revenue":1000},
	{"Month":"Aug","Profit":2500,"Cost":800,"Revenue":1600},
	{"Month":"Sep","Profit":3000,"Cost":1900,"Revenue":1200},
	{"Month":"Oct","Profit":2000,"Cost":2300,"Revenue":1000},
	{"Month":"Nov","Profit":2100,"Cost":2400,"Revenue":1700},
	{"Month":"Dec","Profit":1700,"Cost":1900,"Revenue":2300}
];
