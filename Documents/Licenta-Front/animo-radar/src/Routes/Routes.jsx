import e from "cors";
import React from "react";
import { Redirect, Route, Switch, BrowserRouter } from "react-router-dom";
import { isLoggedIn } from "../Actions/Utils";

import CompaniesList from "../Components/Companies/CompaniesList";
import EmployeeList from "../Components/Employees/EmployeeList";
import AuthMain from "../Components/login/AuthMain"
import ProjectForm from "../Components/Project/ProjectForm";
import Projects from "../Components/Project/Projects";
import TeamCreationForm from "../Components/Project/TeamCreationForm";
import TechnologyMarket from "../Components/Technologies/TechnologyMarket";
import TechnologyPieChart from "../Components/Technologies/TechnologyPieChart";


const PrivateRoute = ({ Component, path }) => (
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
    <PrivateRoute exact path="/companies" component={CompaniesList} />
    <PrivateRoute exact path="/technology" component={TechnologyPieChart} />
    <PrivateRoute exact path="/projects" component={Projects} />
    <PrivateRoute exact path="/projectRegistration" component={ProjectForm}/>
    <PrivateRoute exact path="/projectTeamCreation" component={TeamCreationForm}/>
    <PrivateRoute exact path="/technologyMarket" component={TechnologyMarket}/>
    <PrivateRoute exact path="/employees" component={EmployeeList}/>
  </Switch>
</BrowserRouter>
);

export default Body;