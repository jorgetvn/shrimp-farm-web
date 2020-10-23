import React, { Component} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';

class ShrimpFarmList extends Component {
  constructor(props) {
    super(props);
    this.state = {shrimpFarms: [], isLoading: true};
    this.remove = this.remove.bind(this);
    this.orderTable = this.orderTable.bind(this);
    this.datetimeformat =  Intl.DateTimeFormat("en-GB", {
      year: "numeric",
      month: "long",
      day: "2-digit",
      hour: 'numeric', minute: 'numeric', second: 'numeric'
    });
    this.sorting = [];
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

  orderTable(prop) {
    const propFound = this.sorting.find(p => p.prop === prop);
    if (propFound) {
      if (propFound.direction === "ASC")
        propFound.direction = "DESC"
      else {
        const index = this.sorting.findIndex(p => p === propFound);
        if (index > -1)
          this.sorting.splice(index, 1);
      }
    } else {
      this.sorting.push({
        direction: "ASC",
        prop: prop
      })
    }

    if (this.sorting.length > 0) {
      fetch('api/shrimpfarmssorted', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.sorting)
      }).then(response => response.json())
        .then(data => this.setState({shrimpFarms: data, isLoading: false}));
    } else {
      fetch('api/shrimpfarms')
        .then(response => response.json())
        .then(data => this.setState({shrimpFarms: data, isLoading: false}));
    }
  }

  render() {
    const {shrimpFarms, isLoading} = this.state;
    const datetimeformat = this.datetimeformat;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const shrimpFarmList = shrimpFarms.map(shrimpFarm => {
      return <tr key={shrimpFarm.id}>
        <td style={{whiteSpace: 'nowrap'}}>{shrimpFarm.name}</td>
        <td>{shrimpFarm.ponds.reduce((total, pond) => {
          return total + pond.size;
        }, 0)} ha</td>
        <td>{datetimeformat.format(Date.parse(shrimpFarm.createdDateTime))}</td>
        <td>{datetimeformat.format(Date.parse(shrimpFarm.lastModifiedDateTime))}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="secondary" tag={Link} to={{ pathname: '/ponds/', state: { shrimpFarm } }}>Ponds</Button>
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
                <th width="20%">Name -
                  <Button size="sm" onClick={() => this.orderTable("name")}>Sort</Button>
                </th>
                <th width="20%">Size (Hectares)</th>
                <th width="20%">Created Date -
                  <Button size="sm" onClick={() => this.orderTable("createdDateTime")}>Sort</Button>
                </th>
                <th width="20%">Last Modified Date -
                  <Button size="sm" onClick={() => this.orderTable("lastModifiedDateTime")}>Sort</Button>
                </th>
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