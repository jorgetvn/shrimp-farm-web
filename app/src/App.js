import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    shrimpFarms: []
  }

  async componentDidMount() {
    const response = await fetch('/api/shrimpfarms');
    const body = await response.json();
    console.log(body);
    this.setState({ shrimpFarms: body, isLoading: false });
  }

  render() {
    const {shrimpFarms, isLoading} = this.state;
    if (isLoading) {
      return <p>Loading...</p>;
    }
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Shrimp Farm List</h2>
            {shrimpFarms.map(shrimpFarm =>
              <div key={shrimpFarm.id}>
                {shrimpFarm.name}
              </div>
            )}
          </div>
        </header>
      </div>
    );
  }
}

export default App;
