this.layoutStr = '<rMateChart backgroundColor="#FFFFFF"  borderThickness="1" borderStyle="none">'
	+'<Options>'
		+'<Caption text="Rainfall" />'
		+'<SubCaption text="( mm )" textAlign="right" />'
		+'<Legend />'
	+'</Options>'
	+'<NumberFormatter id="numFmt" precision="0"/>'
	+'<Line2DChart showDataTips="true" dataTipDisplayMode="axis" paddingTop="0">'
		+'<horizontalAxis>'
			+'<CategoryAxis categoryField="Month"/>'
		+'</horizontalAxis>'
		+'<verticalAxis>'
			+'<LinearAxis minimum="0" maximum="100" interval="10" />'
		+'</verticalAxis>'
		+'<series>'
			+'<Line2DSeries yField="Vancouver" displayName="Vancouver">'
				+'<showDataEffect>'
					+ '<SeriesClip duration="1000"/>'
				+'</showDataEffect>'
			+'</Line2DSeries>'
		+'</series>'
		+'<annotationElements>'
			+'<CrossRangeZoomer enableZooming="false" horizontalLabelFormatter="{numFmt}" horizontalStrokeEnable="false"/>'
		+'</annotationElements>'
	+'</Line2DChart>'
+'</rMateChart>';

this.chartData = [{"Month":"Jan","Vancouver":40},
	{"Month":"Feb","Vancouver":50},
	{"Month":"Mar","Vancouver":47},
	{"Month":"Apr","Vancouver":57},
	{"Month":"May","Vancouver":68},
	{"Month":"Jun","Vancouver":52},
	{"Month":"Jul","Vancouver":36},
	{"Month":"Aug","Vancouver":42},
	{"Month":"Sep","Vancouver":33},
	{"Month":"Oct","Vancouver":39},
	{"Month":"Nov","Vancouver":45},
	{"Month":"Dec","Vancouver":40}];
