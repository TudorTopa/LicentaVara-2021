import React, { Fragment } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";


import {
  NavItem,
  NavLink,
} from "reactstrap";
import history from "../../Routes/history";
import "./navbar.scss";


const PAGES_WITHOUT_NAVBAR = ["/auth"];

class NavBar extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      logoutModal: false,
    };

    this.toggleLogoutModal = this.toggleLogoutModal.bind(this);
    this.logoutAction = this.logoutAction.bind(this);
  }

  toggleLogoutModal() {
    this.setState({
      logoutModal: !this.state.logoutModal,
    });
  }

  logoutAction() {
    this.setState({
      logoutModal: !this.state.logoutModal,
    });

    localStorage.setItem("jwt", "");
    localStorage.setItem("role", "");
    history.push("/auth");
  }

  goTo = (page) => {
    console.log("NavBar Click")
    history.push(page);
  };

  render() {
    if (
      PAGES_WITHOUT_NAVBAR.filter(
        (path) => this.props.location.pathname === path
      ).length > 0
    ) {
      return null;
    }

    return (
      <Fragment>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <a className="navbar-brand" href="#">HomePage</a>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div claclassNamess="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav">
          <li className="nav-item active">
          <NavItem>
          <NavLink onClick={() => this.goTo("/companies")}>Companies</NavLink> 
          </NavItem>
          </li>
          <li className="nav-item">
          <NavItem />
          <NavLink onClick={() => this.goTo("/projects")}>Projects</NavLink>
          </li>
          <li className="nav-item">
          <NavItem />
          <NavLink onClick={() => this.goTo("/technology")}>Technology Radar</NavLink>
          </li>
        </ul>
      </div>
    </nav>
    </Fragment>
    
    
    );
  }
}

const mapStateToProps = (state) => {
  return {
    user: state.user
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    ...bindActionCreators({}, dispatch),
  };
};

export default withRouter(
  connect(mapStateToProps, mapDispatchToProps)(NavBar)
);
