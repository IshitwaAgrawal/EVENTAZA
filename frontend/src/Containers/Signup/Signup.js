import React, { Component } from "react";
import classes from "./Signup.module.css";
import axios from "../../Components/axios";
import { withRouter } from "react-router-dom";

const validEmailRegex = RegExp(
  /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i
);
const validateForm = errors => {
  let valid = true;
  Object.values(errors).forEach(val => val.length > 0 && (valid = false));
  return valid;
};

class login extends Component {
  state = {
    name: "",
    email: "",
    username: "",
    password: "",
    roles: "",
    registered: false,
    errors: {
      name: "",
      email: "",
      password: "",
    },
  };
  submit = (e) => {
    e.preventDefault();
    const Data = {
      name: this.state.name,
      username: this.state.username,
      email: this.state.email,
      password: this.state.password,
      roles: "USER"
    };
    axios
      .post("http://a6bf1655597f.ngrok.io/user/registration", Data)
      .then((response) => {
        if (response.status === 200) {
          this.setState({ registered: true });
        }
      })
      .catch((error) => {
        console.log(error);
      });

    this.props.history.push("/login");
  };
  onChangeHandler = (event) => {
    const { name, value } = event.target;
    let errors = this.state.errors;
    this.setState({
      [name]: value,
    });

    switch (name) {
      case "name":
        errors.name =
          value.length < 5 ? "Name must be at least 5 characters long!" : "";
        break;
      case "email":
        errors.email = validEmailRegex.test(value) ? "" : "Email is not valid!";
        break;
      case "password":
        errors.password =
          value.length < 6
            ? "Password must be at least 6 characters long!"
            : "";
        break;
      default:
        break;
    }
    this.setState({ errors, [name]: value });
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
  tologin = () => {
    this.props.history.push("/login");
  };

  render() {
    const { errors } = this.state;
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
              {errors.name.length > 0 && (
                <span className={classes.error}>{errors.name}</span>
              )}
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
              {errors.email.length > 0 && (
                <span className={classes.error}>{errors.email}</span>
              )}
            </div>
            <div>
              <input
                type="password"
                value={this.state.password}
                name="password"
                onChange={this.onChangeHandler}
                placeholder="password"
              ></input>
              {errors.password.length > 0 && (
                <span className={classes.error}>{errors.password}</span>
              )}
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
