import React from "react"
import  {userRegistration}  from "../../Actions/Actions";

export class Register extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            email: "",
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
        onChangeEmail = (event) => this.onChange("email", event.target.value);

        isFormValid = () => {
            if (this.state.username === "" || this.state.password === "" || this.state.email) return false;
            return true;
          };
    
          submitRegisterData = () => {
        userRegistration({
           username: this.state.username,
           email:this.state.email,
           password: this.state.password,
         })
    
    }

    render(){
        return <div className="base-container" ref={this.props.containerRef}>
            <div className="header">Register</div>
            <div className="content">
                <div className="background">
                    
                </div>
                <div className="form">
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="username" name="username" placeholder="Username" value={this.state.username} onChange={this.onChangeUsername}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" name="email" placeholder="Email" value={this.state.email} onChange={this.onChangeEmail}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" placeholder="Password" value={this.state.password} onChange={this.onChangePassword}/>
                    </div>
                </div>
            </div>
            <div className="footer">
                <button className="btn" onClick={() => this.submitRegisterData()}>
                    Register
                </button>
            </div>
        </div>
    }
}
