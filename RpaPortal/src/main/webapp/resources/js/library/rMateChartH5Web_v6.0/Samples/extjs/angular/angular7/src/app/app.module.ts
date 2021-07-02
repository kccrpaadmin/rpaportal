import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SampleButton } from "./components/SampleButton/sample-buttons.component";
import { Welcome } from "./components/Welcome/Welcome.component";
import { ColumnChart } from "./components/ColumnChart/ColumnChart.component";
import { rMateChartH5 } from './components/rMateChartH5/rMateChartH5.component';

@NgModule({
  declarations: [
	AppComponent,
	SampleButton,
	rMateChartH5,
	ColumnChart,
	Welcome
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
