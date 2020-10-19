import React from 'react';
import { act } from 'react-dom/test-utils';
import ReactDOM from 'react-dom';
import AppNavbar from './AppNavbar';
import { BrowserRouter as Router} from 'react-router-dom';

let container;

beforeEach(() => {
  container = document.createElement('div');
  document.body.appendChild(container);
});

afterEach(() => {
  document.body.removeChild(container);
  container = null;
});

test('does it have your github link?', () => {
  act(() => {    
    ReactDOM.render(
      <Router>
        <AppNavbar/>
      </Router>
    , container);
  });
  const navLink = container.querySelector('a.mygithub');
  expect(navLink.href).toBe('https://github.com/torresjorgev');
});
