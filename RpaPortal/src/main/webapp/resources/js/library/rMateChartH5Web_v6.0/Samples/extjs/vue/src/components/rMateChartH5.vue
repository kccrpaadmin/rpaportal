<template>
	<div v-bind:id="holderId" class="chartHolder"></div>
</template>

<script>
	export default{
		props : {
			chartId : { // 차트 아이디
				type : String,
				default : "chart",
			},
			holderId : { // 차트가 그려질 객체 아이디
				type : String,
				default : "chartHolder"
			},
			chartVars : {
				type : String,
				default : ""
			},
			layout : [String, Object], // 차트 레이아웃
			data : [Array, Object] // 차트 데이터
		},
		mounted(){
			this.create();
			this.setLayout(this.layout);
			this.setData(this.data);
		},
		methods : {
			/**
			 * rMateChartH5 객체 생성
			 */
			create : function(){
				rMateChartH5.create(this.chartId, this.holderId, this.chartVars);
			},
			call : function(...params){
				rMateChartH5.call(this.chartId, ...params);
			},
			/**
			 * 레이아웃 설정
			 */
			setLayout : function(layout){
				this.call("setLayout", layout);
			},
			/**
			 * 데이터 설정
			 */
			setData : function(data){
				this.call("setData", data);
			},
			/**
			 * 프리로더 출력
			 */
			showAdditionalPreloader : function(){
				this.call("showAdditionalPreloader");
			},
			/**
			 * 프리로더 삭제
			 */
			removeAdditionalPreloader : function(){
				this.call("removeAdditionalPreloader");
			}
		}
	}
</script>

<style scoped>
	.chartHolder{
		width:100%;
		height:100%;
	}
</style>
