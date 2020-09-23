import React, { Component } from "react";
import classes from "./Signup.module.css";
import axios from "../../Components/axios";
import { withRouter } from "react-router-dom";


class login extends Component {
  state = {
    name: "",
    email: "",
    username: "",
    password: "",
    roles:"",
    registered: false,
  };
  submit = (e) => {
    e.preventDefault();
    const Data = {
      name: this.state.name,
      username: this.state.username,
      email: this.state.email,
      password: this.state.password,
      roles:"USER"
    };
    // const header = {
    //   headers: {
    //       "Access-Control-Request-Method":"POST",
    //     "Content-Type": "application/json",
    //     "Access-Control-Allow-Origin": '*',
    //   },
    // };
    // console.log(Data);
    // axios.post("https://testsignup-88965.firebaseio.com/login.json",Data )
    
      axios.post("http://94c72eedae03.ngrok.io/user/registration", Data)
      .then((response) => {
        if (response.status === 200) {
          this.setState({ registered: true });
          
        }
        // console.log(this.state.registered)
        // this.props.loginname(this.state.name);
      })
      .catch(error => {
          console.log(error);
      });

    // this.props.history.push("/login");
  };
  onChangeHandler = (event) => {
    let name = event.target.name;
    let value = event.target.value;
    this.setState({
      [name]: value,
    });
  };
  tologin = () => {
    this.props.history.push("/login");
  };
  render() {
    return (
      <div className={classes.Form}>
        <main>
          <h4>Signup</h4>
          <form onSubmit={this.submit}>
            <div>
              <input
                type="text"
                value={this.state.name}
                name="name"
                onChange={this.onChangeHandler}
                required
                placeholder="Your name"
              ></input>
            </div>
            <div>
              <input
                type="text"
                value={this.state.username}
                name="username"
                onChange={this.onChangeHandler}
                required
                placeholder="user name"
              ></input>
            </div>
            <div>
              <input
                type="email"
                value={this.state.email}
                name="email"
                onChange={this.onChangeHandler}
                required
                placeholder="Your email"
              ></input>
            </div>
            <div>
              <input
                type="password"
                value={this.state.password}
                name="password"
                onChange={this.onChangeHandler}
                placeholder="password"
              ></input>
            </div>
            <div className={classes.submit}>
              <input
                style={{ background: "transparent", width: "100%" }}
                type="submit"
              ></input>
            </div>
          </form>
          <p onClick={this.tologin}>Already registered?</p>
        </main>
      </div>
    );
  }
}

export default withRouter(login);
