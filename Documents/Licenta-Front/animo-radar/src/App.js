import React, { Fragment } from "react";
import "./App.scss";
import 'bootstrap/dist/css/bootstrap.min.css';
import Body from './Routes/Routes';
import NavBar from "./Components/NavBar/NavBar";
import SnackBar from "./Components/Common/SnackBar";




function App() {
  return (
    <Fragment>
      <NavBar/>
      <Body />
     <SnackBar />
    </Fragment>
  );
}

export default App;