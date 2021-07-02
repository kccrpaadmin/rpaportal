<template>
	<div id="app">
		<div id="sampleButtons">
			<SampleButton v-for="sample in samples" v-bind:key="sample.id" v-bind:sample="sample"></SampleButton>
		</div>
    	<router-view :key="$route.fullPath"/>
  </div>
</template>

<script>

	import SampleButton from "./components/SampleButton.vue";

	export default {
	 	name : 'App',
		props : ["samples"],
		components : {
			SampleButton
		}
	}
	
</script>

<style>
	html, body{
		margin:0;
		padding:0;
	}
	
	*{
		box-sizing:border-box;
	}

	#app{
		width:900px;
		height:500px;
		margin:100px auto;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		display:flex;
	}
	
	#app > div{
		float:left;
	}

	#sampleButtons{
		text-align:left;
		font-size:13px;
		height:100%;
		background-color:#222;
		margin-right:4px;
	}
	
</style>
