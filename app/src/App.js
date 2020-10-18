import React, { Component } from 'react';
import './App.css';
import ShrimpFarmList from './ShrimpFarmList';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={ShrimpFarmList}/>
          <Route path='/shrimpFarms' exact={true} component={ShrimpFarmList}/>
        </Switch>
      </Router>
    );
  }
}

export default App;
