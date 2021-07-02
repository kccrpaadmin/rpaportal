<template>
	<router-link :to="sample.path"><div class="sample-button">{{sample.name}}</div></router-link>
</template>

<script>
	export default{
		props : {
			sample : Object
		}
	}
</script>

<style scoped>
	.sample-button{
		width:160px;
		padding:4px 4px 4px 6px;
		cursor:pointer;
		color:#bdbdbd;
		transition:color 0.2s;
	}
	
	.sample-button:hover, .router-link-active .sample-button{
		color:#fff;
		text-decoration:underline;
	}
</style>
