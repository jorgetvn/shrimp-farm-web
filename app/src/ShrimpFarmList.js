import React, { Component} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';

class ShrimpFarmList extends Component {
  constructor(props) {
    super(props);
    this.state = {shrimpFarms: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});
    fetch('api/shrimpfarms')
      .then(response => response.json())
      .then(data => this.setState({shrimpFarms: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/api/shrimpfarm/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedShrimpFarms = [...this.state.shrimpFarms].filter(i => i.id !== id);
      this.setState({shrimpFarms: updatedShrimpFarms});
    })
  }

  render() {
    const {shrimpFarms, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const shrimpFarmList = shrimpFarms.map(shrimpFarm => {
      return <tr key={shrimpFarm.id}>
        <td style={{whiteSpace: 'nowrap'}}>{shrimpFarm.name}</td>
        <td>Size 0</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/shrimpfarms/" + shrimpFarm.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(shrimpFarm.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/shrimpfarms/new">Add Shrimp Farm</Button>
          </div>
          <h3>My Shrimp Farms</h3>
          <Table className="mt-4">
            <thead>
              <tr>
                <th width="40%">Name</th>
                <th width="40%">Size</th>
                <th width="20%">Actions</th>
              </tr>
            </thead>
            <tbody>
              {shrimpFarmList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default ShrimpFarmList;