import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component {
  render() {
    <div>
      <AppNavbar/>
      <Container fluid>
        <Button color="link"><Link to="/shrimpFarms"></Link></Button>
      </Container>
    </div>
  }
}