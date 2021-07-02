import { Component, Injector } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'test1';
  samples = null;

  constructor(private injector:Injector){
	  this.samples = this.injector.get("samples");
  }
}
