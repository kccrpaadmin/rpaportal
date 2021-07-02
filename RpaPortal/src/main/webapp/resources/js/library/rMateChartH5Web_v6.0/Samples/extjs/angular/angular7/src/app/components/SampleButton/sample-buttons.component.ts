import {Component, OnInit, Input} from "@angular/core";

@Component({
	selector : "SampleButton",
	templateUrl : "./sample-button.component.html",
	styleUrls : ["./sample-button.component.css"]
})

export class SampleButton implements OnInit{
	@Input() sample;

	constructor(){

	}

	ngOnInit(){
		
	}
}