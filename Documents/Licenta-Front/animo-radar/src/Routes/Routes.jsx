import React from "react";
import { Redirect, Route, Switch, BrowserRouter } from "react-router-dom";
import { isLoggedIn } from "../Actions/Utils";

import CompaniesList from "../Components/Companies/CompaniesList";
import AuthMain from "../Components/login/AuthMain"
import ProjectForm from "../Components/Project/ProjectForm";
import Projects from "../Components/Project/Projects";
import TechnologyPieChart from "../Components/Technologies/TechnologyPieChart";


const PrivateRoute = ({ Component, path, isLoggedIn }) => (
    <Route
      path={path}
      render={(props) => {
        if (isLoggedIn()) 
        return <Component {...props} />
        else 
        return <Redirect to="/auth" />;
      }}
    />
  );



const Body = () => (
<BrowserRouter>
  <Switch>
    <Route exact path="/auth" component={AuthMain} />
    <Route exact path="/companies" component={CompaniesList} />
    <Route exact path="/technology" component={TechnologyPieChart} />
    <Route exact path="/projects" component={Projects} />
    <Route exact path="/projectRegistration" component={ProjectForm}/>
  </Switch>
</BrowserRouter>
);

export default Body;