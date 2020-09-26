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
    roles: "",
    registered: false,
    errors: {},
  };
  submit = (e) => {
    e.preventDefault();
    console.log(this.state.errors);
    if (this.validate()) {
      const Data = {
        name: this.state.name,
        username: this.state.username,
        email: this.state.email,
        password: this.state.password,
        roles: "USER",
      };
      axios
        .post("http://b50cd3051760.ngrok.io/user/registration", Data)
        .then((response) => {
          if (response.status === 200) {
            this.setState({ registered: true });
          }
        })
        .catch((error) => {
          console.log(error);
        });

      this.props.history.push("/login");
    }
  };
  onChangeHandler = (event) => {
    const { name, value } = event.target;
    this.setState({
      [name]: value,
    });
    // if ([name] == 'email') {
    //   let input = this.state;
    // let errors = {};
    // let isValid = true;
    //   if (!input["email"]) {
    //     isValid = false;
    //     errors["email"] = "Please enter your email Address.";
    //   }
    // }
    //  this.passvalid();
  };
  //  passwordvalidation='';
  //  passvalid = () =>{
  //   if(this.state.username.length<=5){
  //   this.passwordvalidation='Password must contain 6 letters'
  //   }
  //   if(this.state.username.length>5)
  //   this.passwordvalidation=''
  //  }
  validate() {
    let input = this.state;
    let errors = {};
    let isValid = true;

    if (!input["name"]) {
      isValid = false;
      errors["name"] = "Please enter your name.";
    }

    if (!input["username"]) {
      isValid = false;
      errors["username"] = "Please enter your Username.";
    }

    if (!input["email"]) {
      isValid = false;
      errors["email"] = "Please enter your email Address.";
    }

    if (typeof input["email"] !== "undefined") {
      var pattern = new RegExp(
        /^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i
      );
      if (!pattern.test(input["email"])) {
        isValid = false;
        errors["email"] = "Please enter valid email address.";
      }
    }

    if (input["password"].length < 6) {
      isValid = false;
      errors["password"] = "Please enter your Password.";
    }

    this.setState({
      errors: errors,
    });

    return isValid;
  }

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
                placeholder="Your name"
              ></input>
              <p style={{ color: "red", fontSize: "10px" }}>
                {this.state.errors.name}
              </p>
            </div>
            <div>
              <input
                type="text"
                value={this.state.username}
                name="username"
                onChange={this.onChangeHandler}
                placeholder="user name"
              ></input>
              <p style={{ color: "red", fontSize: "10px" }}>
                {this.state.errors.username}
              </p>
            </div>
            <div>
              <input
                type="email"
                value={this.state.email}
                name="email"
                onChange={this.onChangeHandler}
                placeholder="Your email"
              ></input>
              <p style={{ color: "red", fontSize: "10px" }}>
                {this.state.errors.email}
              </p>
            </div>
            <div>
              <input
                type="password"
                value={this.state.password}
                name="password"
                onChange={this.onChangeHandler}
                placeholder="password"
              ></input>
              <p style={{ color: "red", fontSize: "10px" }}>
                {this.state.errors.password}
              </p>
            </div>
            <div className={classes.submit}>
              <input
                style={{
                  background: "transparent",
                  width: "100%",
                  color: "wheat",
                }}
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
