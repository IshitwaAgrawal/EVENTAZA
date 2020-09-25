import React, { Component } from "react";
import classes from "./Signup.module.css";
import axios from "../../Components/axios";
import { withRouter } from "react-router-dom";

const validEmailRegex = RegExp(
  /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i
);


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
    }
  };
  validateForm = errors => {
    let valid = true;
    Object.values(errors).forEach(val => val.length > 0 && (valid = false));
    return valid;
  };
  submit = (e) => {
    e.preventDefault();
    console.log(this.state.errors.name)
    if(this.validateForm(this.state.errors) === true) {
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
    }else{
      alert('Invalid Form')
    }
    
  };
  onChangeHandler = (event) => {
    const { name, value } = event.target;
    let errors = this.state.errors;
    this.setState({
      [name]:value
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
    this.setState({
      errors:{[name]:value}
    });
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
              {this.state.errors.name.length > 0 && (
                <span className={classes.error}>Name must be at least 5 characters long!</span>
              )}
            </div>
            <div>
              <input
                type="text"
                value={this.state.username}
                name="username"
                onChange={this.onChangeHandler}

                placeholder="user name"
              ></input>
            </div>
            <div>
              <input
                type="email"
                value={this.state.email}
                name="email"
                onChange={this.onChangeHandler}
                placeholder="Your email"
              ></input>
              {this.state.errors.email.length > 0 && (
                <span className={classes.error}>{this.state.errors.email}</span>
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
              {this.state.errors.password.length > 0 && (
                <span className={classes.error}>{this.state.errors.password}</span>
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
