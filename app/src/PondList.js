import React, { Component} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';

class PondList extends Component {
  constructor(props) {
    super(props);
    this.state = {shrimpFarm: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  async componentDidMount() {
    this.setState({isLoading: true});
    const { shrimpFarm } = this.props.location.state;
    const newShrimpFarm = await (await fetch(`/api/shrimpfarm/${shrimpFarm.id}`)).json();
    this.setState({shrimpFarm: newShrimpFarm, isLoading: false});
  }

  async remove(pond) {
    await fetch('/api/pond/', {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(pond),
    }).then(() => {
      let updatedPonds = [...this.state.shrimpFarm.ponds].filter(i => i.id !== pond.id);
      let updatedShrimpFarm = this.state.shrimpFarm;
      updatedShrimpFarm.ponds = updatedPonds;
      this.setState({shrimpFarm: updatedShrimpFarm});
    })
  }

  render() {
    const {shrimpFarm, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const pondList = shrimpFarm.ponds.map(pond => {
      return <tr key={pond.id}>
        <td style={{whiteSpace: 'nowrap'}}>{pond.name}</td>
        <td>{pond.size}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={{ pathname: '/pond/'+ pond.id, state: { shrimpFarm } }}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(pond)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-left">
          <Button color="secondary" tag={Link} to="/shrimpfarms">Back</Button>
          </div>
          <div className="float-right">
            <Button color="success" tag={Link} to={{ pathname: '/pond/new', state: { shrimpFarm } }}>Add Pond</Button>
          </div>
          <h3 style={{"textAlign": "center"}}>Ponds - {shrimpFarm.name}</h3>
          <Table className="mt-4">
            <thead>
              <tr>
                <th width="40%">Name</th>
                <th width="40%">Size</th>
                <th width="20%">Actions</th>
              </tr>
            </thead>
            <tbody>
              {pondList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default PondList;