import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';

import { createStore, applyMiddleware, compose } from "redux";


import thunk from "redux-thunk";
import reducer from './Reducers/reducer';
import { Provider } from 'react-redux';


const composeEnhancers =
  typeof window === "object" && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__
    ? window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({
      })
    : compose;


const enhancer = composeEnhancers(applyMiddleware(thunk));

const store = createStore(reducer, enhancer);

ReactDOM.render(
    <BrowserRouter>
    <Provider store= {store}>
    <App/>
    </Provider>
    </BrowserRouter>,
  document.getElementById('root')
);


reportWebVitals();
