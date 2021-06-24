import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import axios from "axios";
import { push } from 'react-router-redux';
import React from "react"
import {isLoggedIn }  from "../../Actions/Utils";
import { userLogin } from "../../Actions/Actions";
import history from "../../Routes/history";



export class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "", 
          };
      }

      
    
      onChange = (fieldName, value) => {
        this.setState({
          [fieldName]: value,
        });
      };

        onChangeUsername = (event) => this.onChange("username", event.target.value);
        onChangePassword = (event) => this.onChange("password", event.target.value);

        isFormValid = () => {
            if (this.state.username === "" || this.state.password === "") {
              return false;
            }
            else{
            return true;
            }
          };
    
        submitLoginData = () => {
        if(this.isFormValid){
         userLogin({
            username: this.state.username,
            password: this.state.password,
          })
          .then(() => {
          
            if (isLoggedIn) history.push("/companies");
          });
        }
    }

    render(){
        return <div className="base-container" ref={this.props.containerRef}>
            <div className="header">Login</div>
            <div className="content">
                <div className="background">
                </div>
                <div className="form">
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text" name="username" placeholder="Username"  value={this.state.username} onChange={this.onChangeUsername}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" placeholder="Password" value={this.state.password} onChange={this.onChangePassword}/>
                    </div>
                </div>
                
                <div className="footer">
                <button className="btn" type="submit" value="Submit" onClick={() => this.submitLoginData()} >
                    Login
                </button>
            </div>
            </div>
        </div>
    }

}
const mapStateToProps = (state) => {
    return {
      user: state.user,
      snackbar: state.snackbar,
    };
  };
  
  const mapDispatchToProps = (dispatch) => {
    return {
      ...bindActionCreators({ userLogin }, dispatch),
    };
  };
 
  
  export default connect(mapStateToProps, mapDispatchToProps)(Login);


   
