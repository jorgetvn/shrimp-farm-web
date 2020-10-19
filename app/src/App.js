import React, { Component } from 'react';
import './App.css';
import ShrimpFarmList from './ShrimpFarmList';
import ShrimpFarmEdit from './ShrimpFarmEdit';
import PondList from './PondList';
import PondEdit from './PondEdit';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={ShrimpFarmList}/>
          <Route path='/shrimpfarms' exact={true} component={ShrimpFarmList}/>
          <Route path='/shrimpfarms/:id' component={ShrimpFarmEdit}/>
          <Route path='/ponds/' component={PondList}/>
          <Route path='/pond/:id' component={PondEdit}/>
        </Switch>
      </Router>
    );
  }
}

export default App;
