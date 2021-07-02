import React, { Component } from 'react';
import './App.css';
import {
	BrowserRouter as Router,
	Route,
	Switch
} from "react-router-dom";
import SampleButton from "./components/SampleButton";
import RMateChartH5 from "./components/rMateChartH5";
import ColumnChart from './components/ColumnChart';

class App extends Component {
	render(){
		return (
			<Router>
				<div>
					<div id="sampleButtons">
						{
							<SampleButton sample={{
								path : "/default",
								name : "ColumnChart"
							}}/>
						}
						{
							this.props.samples.map((item) => 
								<SampleButton key={item.name} sample={item}/>
							)
						}
					</div>
					<Switch>
						<Route exact path="/" render={() => <div>rMateChartH5 SPA Sample</div>}/>
						<Route path="/default" render={() => <ColumnChart></ColumnChart>}/>
						{
							this.props.samples.map(item =>
								<Route key={item.path} path={item.path} render={props => 
									<RMateChartH5 layout={item.props.layout} data={item.props.data} chartVars={item.props.chartVars}/>
								}/>
							)
						}
					</Switch>
				</div>
			</Router>
		);
	}
}

export default App;
