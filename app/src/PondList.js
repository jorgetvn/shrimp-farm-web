import React, { Component} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';

class PondList extends Component {
  constructor(props) {
    super(props);
    this.state = {ponds: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});
    fetch('/api/ponds')
      .then(response => response.json())
      .then(data => this.setState({ponds: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/api/pond/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedPonds = [...this.state.ponds].filter(i => i.id !== id);
      this.setState({ponds: updatedPonds});
    })
  }

  render() {
    const {ponds, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const pondList = ponds.map(pond => {
      return <tr key={pond.id}>
        <td style={{whiteSpace: 'nowrap'}}>{pond.name}</td>
        <td>{pond.size}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/ponds/" + pond.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(pond.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/ponds/new">Add Pond</Button>
          </div>
          <h3>Ponds</h3>
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