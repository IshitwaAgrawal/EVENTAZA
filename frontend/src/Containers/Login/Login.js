import React, { Component } from "react";
import classes from "./Login.module.css";
import { withRouter } from "react-router-dom";
import axios from "axios";
class signup extends Component {
  state = {
    name:"",
    username: "",
    password: "",

  };

  onChangeHandler = (event) => {
    let name = event.target.name;
    let value = event.target.value;
    this.setState({
      [name]: value,
    });
  };

  submit = (e) => {
    e.preventDefault();
    const Data = {
        username:this.state.username,
        password:this.state.password
    }
    
    axios.post('http://94c72eedae03.ngrok.io/'
    ,Data)
    .then(response => {
        if(response.status === 200)
        {
            console.log(response);
        }
        this.props.loginname(this.state.username);
        this.toRegister();

    }).catch(error => {
        console.log(error);
    })

  }
 



  toRegister = () => {
    this.props.history.push("/register");
  };

  render() {
    return (
      <div className={classes.Login}>
        <div className={classes.Form}>
          <h4>Login</h4>
          <form 
          onSubmit={this.submit}
          >
            <div>
              <input
                type="text"
                name="username"
                value={this.state.username}
                placeholder="username"
                onChange={this.onChangeHandler}
              ></input>
            </div>
            <div>
              <input
                type="password"
                name="password"
                value={this.state.password}
                placeholder="password"
                onChange={this.onChangeHandler}
              ></input>
            </div>
            <div className={classes.submit}>
              <input
                style={{ background: "transparent", width: "100%" }}
                type="submit"
              ></input>
            </div>
          </form>
          <p onClick={this.toRegister}>Not yet registered?</p>
        </div>
      </div>
    );
  }
}
export default withRouter(signup);
