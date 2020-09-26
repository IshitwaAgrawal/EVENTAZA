import React, { Component } from "react";
import classes from "./Header.module.css";
import { Link } from "react-router-dom";

class header extends Component {
  render() {
    let token = localStorage.getItem("jwt");
    let auth = true;
    if (token === null) {
      auth = false;
    }

    const logoutHandler = () => {
      localStorage.clear();
      auth = false;
    };

    return (
      <nav>
        <div className={classes.Header1}>
          <div className={classes.Logo}>
            <h1 style={{ display: "inline block", color: "white" }}>
              Eventaza
            </h1>
            {/* <h1>hello{props.name}</h1> */}
          </div>
          <div className={classes.Link}>
            <div className={classes.CitySelect}>
              <h3>Select city</h3>
              <i className="fas fa-caret-down"></i>
            </div>
            <div className={classes.Login}>
              {auth === false ? (
                <Link to="/login">Login</Link>
              ) : (
                <Link onClick={logoutHandler}>Logout</Link>
              )}
            </div>
            <div className={classes.Toggle}>
              <i className="fas fa-bars"></i>
            </div>
          </div>
          {/* <div  className={classes.Hlink}>
                    <Link  className={classes.a} to='/'>Home</Link>
                    <Link className={classes.a}  to='/register'>Register</Link>
                    <Link  className={classes.a} to='/login'>Login</Link>
                </div> */}
        </div>
        <div className={classes.Header2}>
          <div>
            <h3>dummy</h3>
            <h3>dummy</h3>
            <h3>dummy</h3>
            <h3>dummy</h3>
            <h3>dummy</h3>
          </div>
          <div className={classes.CreateEvent}>
            <Link to="">Create Event</Link>
          </div>
        </div>
      </nav>
    );
  }
}

export default header;
