import React, {Component} from "react";
import PropTypes from "prop-types";
import RMateChart from "./rMateChartH5";

export default class ColumnChart extends Component{
	constructor(props){
		super(props);
	}

	render(){
		return (
			<RMateChart chartId={this.props.chartId} holderId={this.props.holderId} layout={this.props.chartLayout} data={this.props.chartData}/>
		);
	}
}

ColumnChart.propTypes = {
	chartId : PropTypes.string, // 차트 아이디
	holderId : PropTypes.string, // 차트가 그려질 객체 아이디
	chartVars : PropTypes.string,
	chartLayout : PropTypes.object,
	chartData : PropTypes.array
};

ColumnChart.defaultProps = {
	chartId : "basicChart",
	holderId : "basicHolder",
	chartVars : "",
	chartLayout : {
		rMateChart : {
			backgroundColor : "#ffffff",
			borderStyle : "none",
			Options : {
				Caption : {
					text : "World Top 10 - Fastest Growing Economies (2017)"
				},
				SubCaption : {
					text : "GDP Growth (In %)",
					textAlign : "center"
				}
			},
			SeriesInterpolate : {
				id : "ss"
			},
			Column2DChart : {
				showDataTips : true,
				selectionMode : "multiple",
				columnWidthRatio : 0.48,
				horizontalAxis : {
					CategoryAxis : {
						categoryField : "Country"
					}
				},
				verticalAxis : {
					LinearAxis : {
						maximum : 100,
						interval : 10
					}
				},
				series : [{
					Column2DSeries : {
						labelPosition : "outside",
						yField : "GDP",
						displayName : "GDP Growth (In %)",
						showDataEffect : "{ss}",

						// 사용자 정의 함수를 설정할 때는 레이아웃을 지금과 같이 객체 형태로 설정합니다.
						// 스트링 형태로 레이아웃 작성시 사용자 정의 함수는 window 전역함수로 설정되어있어야 합니다.
						outsideLabelJsFunction : function(seriesId, index, data, values){
							return values[1];
						}
					}
				}]
			}
		}
	},
	chartData : [
		{"Country":"South Sudan", "GDP":20},
		{"Country":"Libya", "GDP":30},
		{"Country":"Sierra Leone", "GDP":51.2},
		{"Country":"Mongolia", "GDP":44.5},
		{"Country":"Paraguay", "GDP":62.35},
		{"Country":"Timor Leste", "GDP":84.46},
		{"Country":"Iraq", "GDP":48.9},
		{"Country":"Panama", "GDP":38},
		{"Country":"Gambia", "GDP":60.5},
		{"Country":"Mozam-bique", "GDP":40.1}
	]
};