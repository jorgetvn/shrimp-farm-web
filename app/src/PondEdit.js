import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class PondEdit extends Component {
  emptyItem = {
    name: '',
    size: 0
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const pond = await (await fetch(`/api/pond/${this.props.match.params.id}`)).json();
      this.setState({item: pond});
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;
    const { shrimpFarm} = this.props.location.state;
    item.idShrimpFarm = shrimpFarm.id;

    await fetch(`/api/pond`, {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push({
      pathname: '/ponds',
      state: { shrimpFarm }});
  }

  render() {
    const {item} = this.state;
    const { shrimpFarm } = this.props.location.state;
    const title = <h2>{item.id ? 'Edit Pond' : 'Add Pond'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={item.name || ''}
                   onChange={this.handleChange} autoComplete="name" required/>
          </FormGroup>
          <FormGroup>
            <Label for="name">Size (Hectares)</Label>
            <Input type="number" placeholder="1.0" step="0.01" min="1" name="size" id="size" value={item.size || null}
                   onChange={this.handleChange} autoComplete="size" required/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to={{ pathname: '/ponds/', state: { shrimpFarm } }}>Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(PondEdit);